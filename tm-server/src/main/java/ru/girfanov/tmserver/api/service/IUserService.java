package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.User;

public interface IUserService extends Service<User> {
    void mergePassword(String userId, String newPassword);
    User findOneByLoginAndPassword(String login, String password);
}
