package ru.girfanov.tmserver.api.repository;

import ru.girfanov.tmserver.entity.User;

public interface IUserRepository extends Repository<User> {
    void mergePassword(String userId, String newPassword);
    User findOneByLoginAndPassword(String login, String password);
}
