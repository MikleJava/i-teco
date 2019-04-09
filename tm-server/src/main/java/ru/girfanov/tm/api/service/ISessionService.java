package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Session;

public interface ISessionService {
    Session createSession(String login, String password);
    void removeSession(Session session);
    boolean existSession(Session session);
}
