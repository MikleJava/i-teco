package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Service<T extends AbstractEntity> {
    void persist(T entity, String userId);
    void merge(String uuid, String name, String userId);
    void remove(String uuid, String userId);
    void removeAll(String uuid);
    Collection<T> findAll(String uuid);
    T findOne(String uuid, String userId);
}
