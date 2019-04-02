package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.entity.AbstractEntity;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    @NotNull
    final Map<String, T> map = new LinkedHashMap<>();

    @Override
    public void persist(@NotNull final String userId, @NotNull final T entity) {
        map.put(entity.getUuid(), entity);
    }

    @Override
    public abstract void merge(@NotNull final String userId, @NotNull final T entity);

    @Override
    public abstract void remove(@NotNull final String userId, @NotNull final String uuid);
}
