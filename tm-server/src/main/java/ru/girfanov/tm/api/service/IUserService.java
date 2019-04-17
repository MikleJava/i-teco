package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;

import java.util.List;

public interface IUserService {
    void persist(User user);
    void merge(User user);
    void remove(User user);
    User findOne(String userId);
    List<User> findAll();
    User findOneByLoginAndPassword(String login, String password);
}
