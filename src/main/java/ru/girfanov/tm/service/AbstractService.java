package ru.girfanov.tm.service;

import ru.girfanov.tm.api.Repository;
import ru.girfanov.tm.api.service.Service;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    protected Repository<T> repository;

    @Override
    public void persist(final T entity) {
        if(entity == null) { return; }
        repository.persistEntity(entity);
    }

    @Override
    public void merge(final String uuid, final String name) {
        if(uuid == null || name == null || uuid.isEmpty() || name.isEmpty()) { return; }
        repository.mergeEntityName(uuid, name);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public T findOne(final String uuid) {
        if(uuid == null || uuid.isEmpty()) { return null; }
        return repository.findEntityById(uuid);
    }
}
