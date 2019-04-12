package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public final class UserEndPoint {

    @NonNull private IUserService userService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistUser(@WebParam(name = "user") final User user) throws WrongSessionException {
        userService.persist(user.getUuid(), user);
    }

    @WebMethod
    public void mergeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) throws WrongSessionException {
        sessionService.existSession(session);
        userService.merge(session.getUserId(), user);
    }

    @WebMethod
    public void removeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) throws WrongSessionException {
        sessionService.existSession(session);
        userService.remove(session.getUserId(), user);
    }

    @WebMethod
    public void removeAllUsers(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        userService.removeAllByUserId(session.getUserId());
    }

    @WebMethod
    public User findOneUser(@WebParam(name = "session") final Session session, @WebParam(name = "userUuid") final String uuid) throws WrongSessionException {
        sessionService.existSession(session);
        return userService.findOne(session.getUserId(), uuid);
    }

    @WebMethod
    public List<User> findAllUsers(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        return userService.findAllByUserId(session.getUserId());
    }
    @WebMethod
    public List<User> findAll() {
        return userService.findAll();
    }

    @WebMethod
    public User findOneUserByLoginAndPassword(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return userService.findOneByLoginAndPassword(login, password);
    }
}
