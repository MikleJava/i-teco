package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.Collection;

public interface Service<T extends AbstractEntity> {
    void merge(String userId, T entity) throws UserNotFoundException;
    Collection<T> findAll();
}
