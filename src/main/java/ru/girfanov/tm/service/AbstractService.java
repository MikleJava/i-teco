package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.Service;
import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.User;

import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    @NonNull
    protected Repository<T> repository;

    @Override
    public void persist(@NotNull final T entity, @NotNull final String userId) {
        if(entity.getClass().equals(User.class)) {
            repository.persistEntity(entity);
            return;
        }
        if(repository.findEntityById(userId) == null) { return; }
        repository.persistEntity(entity);
    }

    @Override
    public void merge(@NotNull final String uuid, @NotNull final String name, @NotNull final String userId) {
        if(repository.findEntityById(userId) == null || uuid.isEmpty() || name.isEmpty()) { return; }
        repository.mergeEntityName(uuid, name);
    }

    @Override
    public Collection<T> findAll(@NotNull final String uuid) {
        if(repository.findAllEntitiesById(uuid) == null) { return null; }
        return repository.findAllEntitiesById(uuid);
    }

    @Override
    public T findOne(@NotNull final String uuid, @NotNull final  String userId) {
        if(repository.findEntityById(userId) == null || uuid.isEmpty()) { return null; }
        return repository.findEntityById(uuid);
    }
}
