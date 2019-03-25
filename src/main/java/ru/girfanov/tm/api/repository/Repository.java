package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<T extends AbstractEntity> {
    void persistEntity(T entity);
    void mergeEntityName(String uuid, String name);
    void removeEntityById(String uuid);
    void removeAllEntities();
    Collection<T> findAllEntities();
    T findEntityById(String uuid);
}
