package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;

public interface IUserRepository extends Repository<User> {
    void mergePassword(String userId, String newPassword);
    User findOneByLoginAndPassword(String login, String password);
}
