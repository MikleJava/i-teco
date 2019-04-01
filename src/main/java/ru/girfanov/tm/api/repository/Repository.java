package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.List;

public interface Repository<T extends AbstractEntity> {
    void persist(String userId, T entity);
    void merge(String userId, T entity);
    void remove(String userId, String uuid);
    void removeAll(String userId);
    T findOne(String userId, String uuid);
    List<T> findAll(String userId);
}
