package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.Session;

public interface ISessionService {
    void createSession(String login, String password);
    void removeSession(String sessionId);
    Session getSession(String login, String password);
}
