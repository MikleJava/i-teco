package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import static org.junit.Assert.*;

//public class SessionServiceTest {
//
//    private static SessionService sessionService;
//
//    @NotNull private static final String LOGIN = "test";
//    private Session session;
//
//    @BeforeClass
//    public void setUp() throws UserNotFoundException {
//        sessionService = new SessionService();
//        session = sessionService.createSession(LOGIN);
//    }
//
//    @Test
//    public void testCreateSession() {
//        assertNotNull(session);
//    }
//
//    @Test
//    public void testRemoveSession() throws WrongSessionException, UserNotFoundException {
//        sessionService.removeSession(session);
//        assertFalse(sessionService.existSession(session));
//    }
//
//    @Test
//    public void testExistSession() throws WrongSessionException {
//        assertTrue(sessionService.existSession(session));
//    }
//}