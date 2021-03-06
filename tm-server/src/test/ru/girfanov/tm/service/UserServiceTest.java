package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.util.PasswordHashUtil;
import ru.girfanov.tm.util.SpringJpaConfig;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @NotNull private static final String ID = UUID.randomUUID().toString();
    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";
    @NotNull private static final Role ROLE = Role.USER;

    @Test
    public void testPersist() throws UserNotFoundException {
        final User user = new User();
        user.setId(ID);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        userService.persist(ID, LOGIN, PASSWORD, ROLE);
        final User createdUser = userService.findOne(user.getId());
        assertNotNull(createdUser);
    }

    @Test
    public void testMerge() throws UserNotFoundException {
        final User user = userService.findOneByLogin(LOGIN);
        user.setPassword("newPassword");
        userService.merge(user.getId(), user.getPassword());
        final User updatedUser = userService.findOneByLogin(LOGIN);
        assertEquals(updatedUser.getPassword(), PasswordHashUtil.md5(user.getPassword()));
    }

    @Test(expected = UserNotFoundException.class)
    public void testRemove() throws UserNotFoundException {
        userService.remove(userService.findOneByLogin(LOGIN));
        assertNull(userService.findOneByLogin(LOGIN));
    }

    @Test
    public void testFindOneByLogin() throws UserNotFoundException {
        final User user = userService.findOneByLogin(LOGIN);
        System.out.println(user.getLogin());
        assertNotNull(user);
    }

    @Test
    public void testFindOne() throws UserNotFoundException {
        final User user = userService.findOne(ID);
        assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        final List<User> users = userService.findAll();
        assertNotNull(users);
    }
}