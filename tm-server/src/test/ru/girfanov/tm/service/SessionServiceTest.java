package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.util.SpringJpaConfig;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String WRONG_LOGIN = "qwerfsdf";

    private Session session;

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