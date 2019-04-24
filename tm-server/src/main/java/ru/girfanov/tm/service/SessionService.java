package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.repository.SessionRepository;
import ru.girfanov.tm.repository.UserRepository;
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
    public Session createSession(@NotNull final String login) throws UserNotFoundException {
        if(login.isEmpty()) { return null; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        final SessionRepository sessionRepository = new SessionRepository(em);
        Session session = null;
        try {
            em.getTransaction().begin();
            @Nullable final User user = userRepository.findOneByLogin(login);
            if(user == null) throw new UserNotFoundException("User not found");
            session = new Session();
            session.setTimestamp(getDateISO8601(new Date()));
            session.setUser(user);
            session.setSignature(SignatureUtil.sign(session.getId() + session.getTimestamp(), SALT, CYCLE));
            sessionRepository.persist(session);
            em.getTransaction().commit();
        } catch (ParseException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return session;
    }

    @Override
    public void removeSession(@NotNull final Session session) throws WrongSessionException {
        if(existSession(session)) {
            final EntityManager em = entityManagerFactory.createEntityManager();
            final SessionRepository sessionRepository = new SessionRepository(em);
            try {
                em.getTransaction().begin();
                sessionRepository.remove(session);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
    }

    @Override
    public boolean existSession(@NotNull final Session session) throws WrongSessionException {
        final String signature = session.getSignature();
        if (signature == null || signature.isEmpty()) throw new WrongSessionException("Wrong session");
        session.setSignature(null);
        if (!signature.equals(SignatureUtil.sign(session.getId() + session.getTimestamp(), SALT, CYCLE))) throw new WrongSessionException("Wrong session");
        session.setSignature(signature);
        return true;
    }
}
