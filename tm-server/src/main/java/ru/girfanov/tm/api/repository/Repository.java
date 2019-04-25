package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.User;

import java.util.List;

public interface Repository<T extends AbstractEntity> {
    void persist(T entity);
    void merge(T entity);
    void remove(T entity);
    void removeAllByUser(User user);
    T findOne(User user, String entityId);
    List<T> findAllByUser(User user);
    List<T> findAll();
}
