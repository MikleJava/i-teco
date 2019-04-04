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
    public void persist(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final User entity) {
        userService.persist(userId, entity);
    }

    @WebMethod
    public void merge(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final User entity) {
        userService.merge(userId, entity);
    }

    @WebMethod
    public void remove(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        userService.remove(userId, uuid);
    }

    @WebMethod
    public void removeAll(@WebParam(name = "userId") final String userId) {
        userService.removeAll(userId);
    }

    @WebMethod
    public User findOne(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        return userService.findOne(userId, uuid);
    }

    @WebMethod
    public List<User> findAll(@WebParam(name = "userId") final String userId) {
        return userService.findAll(userId);
    }
}
