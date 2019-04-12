package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;

public interface IUserService extends Service<User> {
    User findOneByLoginAndPassword(String login, String password);
}
