package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.Service;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    @NonNull
    protected Repository<T> repository;

    @Override
    public void persist(@NotNull final String userId, @NotNull final T entity) {
        if(!userId.isEmpty()) { repository.persist(userId, entity); }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final T entity) {
        if(!userId.isEmpty()) { repository.merge(userId, entity); }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String uuid) {
        if(!userId.isEmpty() || !uuid.isEmpty()) { repository.remove(userId, uuid); }
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        if(!userId.isEmpty()) { repository.removeAll(userId); }
    }

    @Override
    public T findOne(@NotNull final String userId, @NotNull final String uuid) {
        if (userId.isEmpty() || uuid.isEmpty()) { return null; }
        return repository.findOne(userId, uuid);
    }

    @Override
    public Collection<T> findAll(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        return repository.findAll(userId);
    }
}
