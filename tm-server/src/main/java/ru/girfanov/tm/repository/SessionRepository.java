package ru.girfanov.tm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, String>, ISessionRepository {
}
