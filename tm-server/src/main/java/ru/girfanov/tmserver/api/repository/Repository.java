package ru.girfanov.tmserver.api.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Repository<T extends Serializable> {
    void persist(String userId, T entity);
    void merge(String userId, T entity);
    void remove(String userId, String uuid);
    void removeAll(String userId);
    T findOne(String userId, String uuid);
    List<T> findAllByUserId(String userId);
    Collection<T> findAll();
}
