package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.api.service.IUserService;
import ru.girfanov.tmserver.entity.Session;
import ru.girfanov.tmserver.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class UserEndPoint {

    @NonNull private IUserService userService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) userService.persist(session.getUserId(), user);
    }

    @WebMethod
    public void mergeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) userService.merge(session.getUserId(), user);
    }

    @WebMethod
    public void removeUser(@WebParam(name = "session") final Session session, @WebParam(name = "userUuid") final String userUuid) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) userService.remove(session.getUserId(), userUuid);
    }

    @WebMethod
    public void removeAllUsers(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) userService.removeAll(session.getUserId());
    }

    @WebMethod
    public User findOneUser(@WebParam(name = "session") final Session session, @WebParam(name = "userUuid") final String uuid) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return userService.findOne(session.getUserId(), uuid);
    }

    @WebMethod
    public List<User> findAllUsers(@WebParam(name = "session") final Session session) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return userService.findAll(session.getUserId());
    }

    @WebMethod
    public void mergeUserPassword(@WebParam(name = "session") final Session session, @WebParam(name = "newPassword") String newPassword) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) userService.mergePassword(session.getUserId(), newPassword);
    }

    @WebMethod
    public User findOneUserByLoginAndPassword(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return userService.findOneByLoginAndPassword(login, password);
    }
}
