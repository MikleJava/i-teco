package ru.girfanov.tmserver.service;

import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.entity.Session;

public class SessionService implements ISessionService {
    @Override
    public void createSession(String login, String password) {

    }

    @Override
    public void removeSession(String sessionId) {

    }

    @Override
    public Session getSession(String login, String password) {
        return null;
    }
}
