package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.repository.temp.SessionRepository;
import ru.girfanov.tm.repository.temp.UserRepository;
import ru.girfanov.tm.util.SignatureUtil;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static ru.girfanov.tm.util.DateFormatUtil.getDateISO8601;

import java.text.ParseException;
import java.util.Date;

@ApplicationScoped
@NoArgsConstructor
public class SessionService implements ISessionService {

    @Inject private EntityManagerFactory entityManagerFactory;

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
    public void removeSession(@NotNull final Session session) throws UserNotFoundException {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        final SessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(session.getUser().getId());
            if(user == null) throw new UserNotFoundException("User not found");
            sessionRepository.remove(session);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existSession(@NotNull final SessionDto sessionDto) throws WrongSessionException {
        final String signature = sessionDto.getSignature();
        if (signature == null || signature.isEmpty()) throw new WrongSessionException("Wrong session");
        sessionDto.setSignature(null);
        if (!signature.equals(SignatureUtil.sign(sessionDto.getId() + sessionDto.getTimestamp(), SALT, CYCLE))) throw new WrongSessionException("Wrong session");
        sessionDto.setSignature(signature);
        return true;
    }
}
