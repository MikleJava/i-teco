package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    final Map<String, T> map = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(final T entity) {
        map.put(entity.getUuid(), entity);
    }

    @Override
    public Collection<T> findAllEntities() {
        return map.values();
    }

    @Override
    public T findEntityById(String uuid) {
        T entity = null;
        for(Map.Entry<String, T> entry : map.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                entity = entry.getValue();
            }
        }
        return entity;
    }
}
