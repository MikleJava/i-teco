package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.Service;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    @NonNull protected Repository<T> repository;

    @Override
    public void persist(@NotNull final String userId, @NotNull final T entity) {
        if(!userId.isEmpty()) { repository.persist(entity); }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final T entity) {
        if(!userId.isEmpty()) { repository.merge(entity); }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final T entity) {
        if(!userId.isEmpty()) { repository.remove(entity); }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(!userId.isEmpty()) { repository.removeAllByUserId(userId); }
    }

    @Nullable
    @Override
    public T findOne(@NotNull final String userId, @NotNull final String uuid) {
        if (userId.isEmpty() || uuid.isEmpty()) { return null; }
        return repository.findOne(userId, uuid);
    }

    @Nullable
    @Override
    public List<T> findAllByUserId(@NotNull final String userId) {
        if(!userId.isEmpty()) { return null; }
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
