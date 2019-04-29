package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.User;

public interface SessionRepository extends EntityRepository<Session, String>, ISessionRepository {
    @Override
    void persist(@NotNull final Session session);

    @Override
    void remove(@NotNull final Session session);

    @Override
    @Query("SELECT s FROM Session s WHERE s.user_id = :userId AND s.id = :sessionId")
    Session findOne(@QueryParam("userId") @NotNull final User userId, @QueryParam("sessionId") @NotNull final String sessionId);
}
