package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class SessionEndPoint {

    @NonNull private ISessionService sessionService;

    @WebMethod
    public Session createSession(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return sessionService.createSession(login, password);
    }

    @WebMethod
    public void removeSession(@WebParam(name = "session") final Session session) throws WrongSessionException {
        if(sessionService.existSession(session)) sessionService.removeSession(session);
    }
}
