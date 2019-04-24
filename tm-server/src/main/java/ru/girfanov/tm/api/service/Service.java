package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface Service<T extends AbstractEntity> {
    void persist(String userId, T entity) throws UserNotFoundException;
    void merge(String userId, T entity) throws UserNotFoundException;
    void remove(String userId, T entity) throws UserNotFoundException;
    void removeAllByUserId(String userId) throws UserNotFoundException;
    T findOne(String userId, String uuid) throws UserNotFoundException;
    List<T> findAllByUserId(String userId) throws UserNotFoundException;
    List<T> findAll();
}
