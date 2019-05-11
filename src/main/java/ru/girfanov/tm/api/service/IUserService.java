package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

public interface IUserService extends Service<User> {
    void persist(User user);
    void remove(String userId, User user) throws UserNotFoundException;
    User findOne(String userId) throws UserNotFoundException;
    User findOneByLoginAndPassword(String login, String password) throws UserNotFoundException;
}