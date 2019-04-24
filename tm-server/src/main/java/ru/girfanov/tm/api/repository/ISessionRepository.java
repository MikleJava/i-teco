package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Session;

public interface ISessionRepository {
    void persist(Session session);
    void remove(Session session);
    Session findOne(String userId, String sessionId);
}
