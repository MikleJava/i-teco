package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.HibernateConnectorUtil;
import ru.girfanov.tm.util.PasswordHashUtil;
import ru.girfanov.tm.util.SignatureUtil;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static ru.girfanov.tm.util.DateFormatUtil.getDateISO8601;

import java.text.ParseException;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
public final class SessionService implements ISessionService {

    @NonNull private EntityManagerFactory entityManagerFactory;

    @Nullable private static final String SALT = PropertyService.getSalt();
    @Nullable private static final Integer CYCLE = Integer.valueOf(PropertyService.getCycle());

    @Override
    @Nullable
    public Session createSession(@NotNull final String login, @NotNull final String password) throws UserNotFoundException {
        if(login.isEmpty() || password.isEmpty()) { return null; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        Session session = null;
        try {
            final UserRepository userRepository = new UserRepository(em);
            @Nullable final User user = userRepository.findOneByLoginAndPassword(login, PasswordHashUtil.md5(password));
            if(user == null) throw new UserNotFoundException("UserDto not found");
            session = new Session();
            session.setTimestamp(getDateISO8601(new Date()));
            session.setUserId(user.getId());
            session.setSignature(SignatureUtil.sign(session, SALT, CYCLE));
            final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            sessionRepository.persist(session);
            sqlSession.commit();
        } catch (ParseException e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
        } finally {
            sqlSession.close();
        }
        return session;
    }

    @Override
    public void removeSession(@NotNull final Session session) throws WrongSessionException {
        existSession(session);
        try(final SqlSession sqlSession = new HibernateConnectorUtil().getSqlSessionFactory().openSession()) {
            final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            sessionRepository.remove(session);
            sqlSession.commit();
        }
    }

    @Override
    public boolean existSession(@NotNull final Session session) throws WrongSessionException {
        final String signature = session.getSignature();
        if (signature == null || signature.isEmpty()) throw new WrongSessionException("Wrong session");
        session.setSignature(null);
        if (!signature.equals(SignatureUtil.sign(session, SALT, CYCLE))) throw new WrongSessionException("Wrong session");
        session.setSignature(signature);
        return true;
    }
}
