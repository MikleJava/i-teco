package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import static org.junit.Assert.*;

public class SessionServiceTest {

    private static SessionService sessionService;

    @NotNull private static final String LOGIN = "test";
    private Session session;

    @BeforeClass
    public void setUp() throws UserNotFoundException {
        sessionService = new SessionService();
        session = sessionService.createSession(LOGIN);
    }

    @Test
    public void testCreateSession() {
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