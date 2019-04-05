package ru.girfanov.tmserver.api.repository;

import ru.girfanov.tmserver.entity.Session;

public interface ISessionRepository {
    void createSession(String login, String password);
    void removeSession(String sessionId);
    Session getSession(String login, String password);
}
