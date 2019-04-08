package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.Session;

public interface ISessionService {
    Session createSession(String login, String password);
    void removeSession(String userId, String sessionId);
    boolean existSession(String userId, String sessionId);
}
