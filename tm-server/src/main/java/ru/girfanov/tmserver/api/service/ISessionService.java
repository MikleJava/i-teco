package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.Session;

public interface ISessionService {
    Session createSession(String login, String password);
    void removeSession(String sessionId);
    boolean existSession(Session session);
}
