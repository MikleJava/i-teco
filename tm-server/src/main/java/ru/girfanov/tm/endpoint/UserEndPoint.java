package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
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
            if(sessionService.existSession(session)) userService.merge(user);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeUser(@WebParam(name = "session") final Session session, @WebParam(name = "user") final User user) {
        try {
            if(sessionService.existSession(session)) userService.remove(user);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public User findOneUser(@WebParam(name = "session") final Session session, @WebParam(name = "userUuid") final String userId) {
        try {
            if(sessionService.existSession(session)) return userService.findOne(userId);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<User> findAllUsers(@WebParam(name = "session") final Session session) {
        try {
            if(sessionService.existSession(session)) return userService.findAll();
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @WebMethod //for serialization
    public List<User> findAll() {
        return userService.findAll();
    }

    @Nullable
    @WebMethod
    public User findOneUserByLogin(@WebParam(name = "login") final String login) {
        try {
            return userService.findOneByLogin(login);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
