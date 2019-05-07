package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    void persist(String id, String login, String password, Role role);
    void merge(String userId, String password);
    void remove(User user);
    User findOne(String userId) throws UserNotFoundException;
    List<User> findAll();
    User findOneByLogin(String login) throws UserNotFoundException;
}
