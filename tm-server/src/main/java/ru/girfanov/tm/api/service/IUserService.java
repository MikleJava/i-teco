package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;

public interface IUserService extends Service<User> {
    void mergePassword(String userId, String newPassword);
    User findOneByLoginAndPassword(String login, String password);
}
