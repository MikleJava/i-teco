package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.util.HibernateConnectorUtil;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserServiceTest {

    private static UserService userService;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";
    @NotNull private static final String NEW_PASSWORD = "newPassword";
    @NotNull private static final String ROLE = "USER";

    @BeforeClass
    public static void setUp() {
        userService = new UserService(HibernateConnectorUtil.factory());
    }

    @Test
    public void testPersist() throws UserNotFoundException {
        final User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setRole(Role.valueOf(ROLE));
        userService.persist(user);
        final User createdUser = userService.findOne(user.getId());
        assertNotNull(createdUser);
        assertTrue(user.getId().equals(createdUser.getId()) && user.getLogin().equals(createdUser.getLogin()) && user.getPassword().equals(createdUser.getPassword()) && user.getRole().equals(createdUser.getRole()));
    }

    @Test
    public void testMerge() throws UserNotFoundException {
        final User user = userService.findOneByLogin(LOGIN);
        user.setPassword(NEW_PASSWORD);
        userService.merge(user);
        final User createdUser = userService.findOneByLogin(LOGIN);
        assertTrue(user.getId().equals(createdUser.getId()) && user.getLogin().equals(createdUser.getLogin()) && user.getPassword().equals(createdUser.getPassword()) && user.getRole().equals(createdUser.getRole()));
    }

    @Test(expected = NoResultException.class)
    public void testRemove() throws UserNotFoundException {
        final User user = userService.findOneByLogin(LOGIN);
        userService.remove(user);
        assertNull(userService.findOneByLogin(LOGIN));
    }

    @Test
    public void testRemoveAllByUserId() {
        //TODO
    }

    @Test
    public void testFindOneByLoginAndPassword() throws UserNotFoundException {
        final User user = userService.findOneByLogin(LOGIN);
        System.out.println(user.getLogin());
        assertNotNull(user);
    }

    @Test
    public void testFindOne() throws UserNotFoundException {
        final User user = userService.findOne(userService.findOneByLogin(LOGIN).getId());
        System.out.println(user.getLogin());
        assertNotNull(user);
    }

    @Test
    public void testFindAllByUserId() {
        //TODO
    }

    @Test
    public void testFindAll() {
        final List<User> users = userService.findAll();
        assertNotNull(users);
    }
}