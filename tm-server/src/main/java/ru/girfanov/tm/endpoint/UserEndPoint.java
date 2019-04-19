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
    public void persistUser(@WebParam(name = "user") final User user) {
        userService.persist(user);
    }

    @WebMethod
    public void mergeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        userService.merge(user);
    }

    @WebMethod
    public void removeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        userService.remove(user);
    }

    @WebMethod
    public User findOneUser(@WebParam(name = "session") final Session session, @WebParam(name = "userUuid") final String userId) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return userService.findOne(userId);
    }

    @WebMethod
    public List<User> findAllUsers(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return userService.findAll();
    }

    @WebMethod //for serialization
    public List<User> findAll() {
        return userService.findAll();
    }

    @WebMethod
    public User findOneUserByLoginAndPassword(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return userService.findOneByLoginAndPassword(login, password);
    }
}
