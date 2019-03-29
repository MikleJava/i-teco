package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    @NotNull
    final Map<String, T> map = new ConcurrentHashMap<>();

    @Override
    public void persist(@NotNull final String userId, @NotNull final T entity) {
        //if(entity.getClass().equals(User.class)) { map.put(entity.getUuid(), entity); }
        //if(map.containsKey(userId)) {
            map.put(entity.getUuid(), entity);
        //}
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final T entity) {
        persist(userId, entity);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String uuid) {
        //if(map.containsKey(userId)) {
            map.remove(uuid);
        //}
    }

    @Override
    public T findOne(@NotNull final String userId, @NotNull final String uuid) {
        //if(!map.containsKey(userId)) { return null; }
        return map.get(uuid);
    }
}
