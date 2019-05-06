package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongHostException;
import ru.girfanov.tm.exception.WrongPortException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Component
@WebService
@NoArgsConstructor
public class SessionEndPoint {

    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUserService userService;

    @Nullable
    @WebMethod
    public SessionDto createSession(@WebParam(name = "login") final String login) {
        SessionDto sessionDto = null;
        try {
            sessionDto = castToSessionDto(sessionService.createSession(login));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return sessionDto;
    }

    @WebMethod
    public void removeSession(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            sessionService.removeSession(castToSession(sessionDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public String getServerInfo() {
        try {
            return sessionService.getServerInfo();
        } catch (WrongPortException | WrongHostException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private SessionDto castToSessionDto(@NotNull final Session session) {
        final SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setTimestamp(session.getTimestamp());
        sessionDto.setSignature(session.getSignature());
        sessionDto.setUserId(session.getUser().getId());
        return sessionDto;
    }

    private Session castToSession(@NotNull final SessionDto sessionDto) {
        final Session session = new Session();
        session.setId(sessionDto.getId());
        session.setTimestamp(sessionDto.getTimestamp());
        session.setSignature(sessionDto.getSignature());
        try {
            session.setUser(userService.findOne(sessionDto.getUserId()));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return session;
    }
}
