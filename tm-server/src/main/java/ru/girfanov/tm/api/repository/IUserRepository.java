package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
//    void persist(User user);
    void merge(String userId, String password);
//    void remove(User user);
//    Optional<User> findOne(String userId) throws UserNotFoundException;
//    List<User> findAll();
    Optional<User> findByLogin(String login);
}
