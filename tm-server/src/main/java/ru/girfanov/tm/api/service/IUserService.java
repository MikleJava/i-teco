package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    void persist(User user);
    void merge(User user);
    void remove(User user);
    User findOne(String userId) throws UserNotFoundException;
    List<User> findAll();
    User findOneByLogin(String login) throws UserNotFoundException;
}
