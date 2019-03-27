package ru.girfanov.tm.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.Service;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    @NonNull
    protected Repository<T> repository;

    @Override
    public void persist(@NotNull final T entity) {
        repository.persistEntity(entity);
    }

    @Override
    public void merge(@NotNull final String uuid, @NotNull final String name) {
        if(uuid.isEmpty() || name.isEmpty()) { return; }
        repository.mergeEntityName(uuid, name);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public T findOne(@NotNull final String uuid) {
        if(uuid.isEmpty()) { return null; }
        return repository.findEntityById(uuid);
    }
}
