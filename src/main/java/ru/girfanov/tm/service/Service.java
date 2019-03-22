package ru.girfanov.tm.service;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Service<T extends AbstractEntity> {
    void persist(T entity);
    void merge(String uuid, String name);
    void remove(String uuid);
    void removeAll();
    Collection<T> findAll();
    T findOne(String uuid);
}
