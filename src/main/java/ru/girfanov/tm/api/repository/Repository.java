package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<T extends AbstractEntity> {
    void persist(T entity);
    void remove(T entity);
    void merge(T entity);
    T findOne(String entityId);
    Collection<T> findAll();
}