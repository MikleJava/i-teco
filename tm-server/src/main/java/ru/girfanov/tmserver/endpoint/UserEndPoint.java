package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.IUserService;
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

    @WebMethod
    public void persistUser(@WebParam(name = "userId") final String userId, @WebParam(name = "user") final User user) {
        userService.persist(userId, user);
    }

    @WebMethod
    public void mergeUser(@WebParam(name = "userId") final String userId, @WebParam(name = "user") final User user) {
        userService.merge(userId, user);
    }

    @WebMethod
    public void removeUser(@WebParam(name = "userId") final String userId, @WebParam(name = "userUuid") final String userUuid) {
        userService.remove(userId, userUuid);
    }

    @WebMethod
    public void removeAllUsers(@WebParam(name = "userId") final String userId) {
        userService.removeAll(userId);
    }

    @WebMethod
    public User findOneUser(@WebParam(name = "userId") final String userId, @WebParam(name = "userUuid") final String uuid) {
        return userService.findOne(userId, uuid);
    }

    @WebMethod
    public List<User> findAllUsers(@WebParam(name = "userId") final String userId) {
        return userService.findAll(userId);
    }

    @WebMethod
    public void mergeUserPassword(@WebParam(name = "userId") final String userId, @WebParam(name = "newPassword") String newPassword) {
        userService.mergePassword(userId, newPassword);
    }

    @WebMethod
    public User findOneUserByLoginAndPassword(@WebParam(name = "login") final String login, @WebParam(name = "password") final String password) {
        return userService.findOneByLoginAndPassword(login, password);
    }
}
