package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;

import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

public class UserServiceTest {

    private static UserService userService;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";
    @NotNull private static final String NEW_PASSWORD = "newPassword";
    @NotNull private static final String ROLE = "USER";

    @BeforeClass
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testPersist() {
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
    public void testMerge() {
        final User user = userService.findOneByLoginAndPassword(LOGIN, PASSWORD);
        user.setPassword(NEW_PASSWORD);
        userService.merge(user);
        final User createdUser = userService.findOneByLoginAndPassword(LOGIN, NEW_PASSWORD);
        assertNotNull(createdUser);
        assertTrue(user.getId().equals(createdUser.getId()) && user.getLogin().equals(createdUser.getLogin()) && user.getPassword().equals(createdUser.getPassword()) && user.getRole().equals(createdUser.getRole()));
    }

    @Test
    public void testRemove() {
        final User user = userService.findOneByLoginAndPassword(LOGIN, PASSWORD);
        userService.remove(user);
        assertNull(userService.findOneByLoginAndPassword(LOGIN, PASSWORD));
    }

    @Test
    public void testRemoveAllByUserId() {
        //TODO
    }

    @Test
    public void testFindOne() {
        //TODO
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

    @Test
    public void testFindOneByLoginAndPassword() {
        final User user = userService.findOneByLoginAndPassword(LOGIN, PASSWORD);
        assertNotNull(user);
    }
}