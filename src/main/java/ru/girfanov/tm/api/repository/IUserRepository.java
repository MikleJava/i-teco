package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;

public interface IUserRepository extends Repository<User> {
    void mergeEntityPassword(String uuid, String newPassword);
    User findOneEntityByLoginAndPassword(String login, String password);
}
