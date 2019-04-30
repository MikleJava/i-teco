package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface Service<T extends AbstractEntity> {
    void persist(User userId, T entity) throws UserNotFoundException;
    void merge(User userId, T entity) throws UserNotFoundException;
    void remove(User userId, T entity) throws UserNotFoundException;
    void removeAllByUserId(User userId) throws UserNotFoundException;
    T findOne(User userId, String uuid) throws UserNotFoundException;
    List<T> findAllByUserId(User userId) throws UserNotFoundException;
    List<T> findAll();
}
