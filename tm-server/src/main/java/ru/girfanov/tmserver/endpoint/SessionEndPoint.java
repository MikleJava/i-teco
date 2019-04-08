package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class SessionEndPoint {

    @NonNull
    private ISessionService sessionService;

    @WebMethod
    public Session createSession(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return sessionService.createSession(login, password);
    }

    @WebMethod
    public void removeSession(@WebParam(name = "userId") final String userId, @WebParam(name = "sessionId") final String sessionId) {
        if(sessionService.existSession(userId, sessionId)) sessionService.removeSession(userId, sessionId);
    }
}
