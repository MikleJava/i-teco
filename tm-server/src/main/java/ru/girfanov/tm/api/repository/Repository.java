package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface Repository<T extends AbstractEntity> {
    void persist(T entity);
    void merge(T entity);
    void remove(T entity);
    void removeAllByUserId(String userId);
    T findOne(String userId, String entityId);
    List<T> findAllByUserId(String userId);
    Collection<T> findAll();
}
