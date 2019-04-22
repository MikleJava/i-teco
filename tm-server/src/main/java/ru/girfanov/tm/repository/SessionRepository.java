package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

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
    public void merge(@NotNull final Session session) {
        em.merge(session);
    }

    @Override
    public void remove(@NotNull final Session session) {
        em.remove(session);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        em.createQuery("DELETE FROM Session WHERE user = :user_id").setParameter("user_id", userId);
    }

    @Override
    public Session findOne(@NotNull final String userId, @NotNull final String sessionId) {
        return em.createQuery("SELECT t FROM Session t WHERE t.user = :user_id AND t.id = :id", Session.class).setParameter("user_id", userId).setParameter("id", sessionId).getSingleResult();
    }

    @Override
    public List<Session> findAllByUserId(@NotNull final String userId) {
        return em.createQuery("SELECT t FROM Session t WHERE t.user = :user_id", Session.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Session> findAll() {
        return em.createQuery("SELECT t FROM Session t", Session.class).getResultList();
    }
}
