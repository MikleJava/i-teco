package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Component
@WebService
@NoArgsConstructor
public class UserEndPoint {

    @Autowired
    private IUserService userService;
    @Autowired
    private ISessionService sessionService;

    @WebMethod
    public void persistUser(@WebParam(name = "id") @NotNull final String id, @WebParam(name = "login") @NotNull final String login, @WebParam(name = "password") @NotNull final String password, @WebParam(name = "role") @NotNull final Role role) {
        userService.persist(id, login, password, role);
    }

    @WebMethod
    public void mergeUser(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "user") final UserDto userDto) {
        try {
            if(sessionService.existSession(sessionDto)) userService.merge(userDto.getId(), userDto.getPassword());
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeUser(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "user") final UserDto userDto) {
        try {
            if(sessionService.existSession(sessionDto)) userService.remove(castToUser(userDto));
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public UserDto findOneUser(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "userUuid") final String userId) {
        try {
            if(sessionService.existSession(sessionDto)) return castToUserDto(userService.findOne(userId));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<UserDto> findAllUsers(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            if(sessionService.existSession(sessionDto)) return castToListUsersDto(userService.findAll());
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @WebMethod //for serialization
    public List<UserDto> findAll() {
        return castToListUsersDto(userService.findAll());
    }

    @Nullable
    @WebMethod
    public UserDto findOneUserByLogin(@WebParam(name = "login") final String login) {
        try {
            return castToUserDto(userService.findOneByLogin(login));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private UserDto castToUserDto(@NotNull final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private User castToUser(@NotNull final UserDto userDto) {
        final User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    private List<UserDto> castToListUsersDto(@NotNull final List<User> users) {
        final List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(castToUserDto(user));
        }
        return usersDto;
    }
}
