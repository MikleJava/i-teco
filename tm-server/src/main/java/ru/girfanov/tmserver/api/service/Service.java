package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.AbstractEntity;

import java.util.List;

public interface Service<T extends AbstractEntity> {
    void persist(String userId, T entity);
    void merge(String userId, T entity);
    void remove(String userId, String uuid);
    void removeAll(String userId);
    T findOne(String userId, String uuid);
    List<T> findAll(String userId);
}
