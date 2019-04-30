package ru.girfanov.tm.service;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(CdiTestRunner.class)
public class SessionServiceTest {

    @Inject
    private SessionService sessionService;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String WRONG_LOGIN = "qwerfsdf";

    private static Session session;

    @Test
    public void testCreatedSession() throws UserNotFoundException {
        session = sessionService.createSession(LOGIN);
        assertNotNull(session);
    }

    @Test(expected = UserNotFoundException.class)
    public void testErrorCreatedSession() throws UserNotFoundException {
        session = sessionService.createSession(WRONG_LOGIN);
        assertNotNull(session);
    }


    @Test
    public void testRemoveSession() throws WrongSessionException, UserNotFoundException {
        sessionService.removeSession(session);
        assertFalse(sessionService.existSession(castToSessionDto(session)));
    }

    @Test
    public void testExistSession() throws WrongSessionException {
        assertTrue(sessionService.existSession(castToSessionDto(session)));
    }

    private SessionDto castToSessionDto(@NotNull final Session session) {
        final SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setTimestamp(session.getTimestamp());
        sessionDto.setSignature(session.getSignature());
        sessionDto.setUserId(session.getUser().getId());
        return sessionDto;
    }
}