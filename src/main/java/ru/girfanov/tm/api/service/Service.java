package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Service<T extends AbstractEntity> {
    void persist(String userId, T entity);
    void merge(String userId, T entity);
    void remove(String userId, String uuid);
    void removeAll(String userId);
    T findOne(String userId, String uuid);
    Collection<T> findAll(String userId);
}
