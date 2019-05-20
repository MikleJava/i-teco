package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.User;

import java.util.Optional;

public interface IUserRepository extends Repository<User> {
    Optional<User> findByLogin(String login);
}
