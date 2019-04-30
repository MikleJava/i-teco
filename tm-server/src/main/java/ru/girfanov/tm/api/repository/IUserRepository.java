package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface IUserRepository {
    void persist(User user);
    void merge(User user);
    void remove(User user);
    User findOne(String userId) throws UserNotFoundException;
    List<User> findAll();
    User findOneByLogin(String login);
}
