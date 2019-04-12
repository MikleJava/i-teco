package ru.girfanov.tm.api.service;

import java.io.Serializable;
import java.util.List;

public interface Service<T extends Serializable> {
    void persist(String userId, T entity);
    void merge(String userId, T entity);
    void remove(String userId, T entity);
    void removeAllByUserId(String userId);
    T findOne(String userId, String uuid);
    List<T> findAllByUserId(String userId);
    List<T> findAll();
}
