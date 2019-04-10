package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;

public interface IUserRepository extends Repository<User> {
    User findOneByLoginAndPassword(String login, String password);
}
