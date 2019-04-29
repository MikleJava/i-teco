package ru.girfanov.tm.repository.temp;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public class SessionRepository implements ISessionRepository {

    @NonNull private EntityManager em;

    @Override
    public void persist(@NotNull final Session session) {
        em.persist(session);
    }

    @Override
    public void remove(@NotNull final Session session) {
        em.remove(session);
    }

    public void removeAllByUserId(@NotNull final User userId) {
        em.createQuery("DELETE FROM Session WHERE user = :user_id").setParameter("user_id", userId);
    }

    @Nullable
    @Override
    public Session findOne(@NotNull final User userId, @NotNull final String sessionId) {
        final List<Session> sessions = em.createQuery("SELECT t FROM Session t WHERE t.user = :user_id AND t.id = :id", Session.class).setParameter("user_id", userId).setParameter("id", sessionId).getResultList();
        for(Session session : sessions) {
            if(session != null) return session;
        }
        return null;
    }

    public Session findOneByUser(@NotNull final User user) {
        final List<Session> sessions = em.createQuery("SELECT t FROM Session t WHERE t.user = :user_id", Session.class).setParameter("user_id", user).getResultList();
        for(Session session : sessions) {
            if(session != null) return session;
        }
        return null;
    }

    public List<Session> findAllByUserId(@NotNull final User userId) {
        return em.createQuery("SELECT t FROM Session t WHERE t.user = :user_id", Session.class).setParameter("user_id", userId).getResultList();
    }

    public List<Session> findAll() {
        return em.createQuery("SELECT t FROM Session t", Session.class).getResultList();
    }
}
