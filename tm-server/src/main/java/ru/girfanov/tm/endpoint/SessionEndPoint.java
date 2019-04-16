package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class SessionEndPoint {

    @NonNull private ISessionService sessionService;

    @Nullable
    @WebMethod
    public Session createSession(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        Session session = null;
        try {
            session = sessionService.createSession(login, password);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    @WebMethod
    public void removeSession(@WebParam(name = "session") final Session session) {
        try {
            sessionService.removeSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
    }
}
