package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.User;

public interface IUserService extends Service<User> {
    void mergeUserPassword(String uuid, String newPassword);
    User findOneByLoginAndPassword(String login, String password);
}
