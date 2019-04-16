package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

public interface ISessionService {
    Session createSession(String login, String password) throws UserNotFoundException;
    void removeSession(Session session) throws WrongSessionException;
    boolean existSession(Session session) throws WrongSessionException;
}
