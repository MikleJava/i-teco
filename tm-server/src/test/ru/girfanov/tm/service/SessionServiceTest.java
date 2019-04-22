package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import static org.testng.Assert.*;

public class SessionServiceTest {

    private static SessionService sessionService;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";
    private Session session;

    @BeforeClass
    public void setUp() throws UserNotFoundException {
        sessionService = new SessionService();
        session = sessionService.createSession(LOGIN, PASSWORD);
    }

    @Test
    public void testCreateSession() {
        assertNotNull(session);
    }

    @Test
    public void testRemoveSession() throws WrongSessionException {
        sessionService.removeSession(session);
        assertFalse(sessionService.existSession(session));
    }

    @Test
    public void testExistSession() throws WrongSessionException {
        assertTrue(sessionService.existSession(session));
    }
}