package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;

@Repository
public interface SessionRepository extends EntityRepository<Session, String>, ISessionRepository {
    @Override
    void persist(@NotNull final Session session);

    @Override
    void remove(@NotNull final Session session);

    @Override
    @Query(value = "SELECT s FROM Session s WHERE s.user = :userId AND s.id = :sessionId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")}, singleResult = SingleResultType.OPTIONAL)
    Session findOne(@QueryParam("userId") @NotNull final User userId, @QueryParam("sessionId") @NotNull final String sessionId);
}
