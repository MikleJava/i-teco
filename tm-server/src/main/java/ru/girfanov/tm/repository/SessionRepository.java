package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public void merge(@NotNull String userId, @NotNull Session entity) {
        //TODO
    }

    @Override
    public void remove(@NotNull String userId, @NotNull String uuid) {
        if(findOne(userId, uuid) == null) return;
        map.remove(uuid);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        //TODO
    }

    @Override
    public Session findOne(@NotNull final String userId, @NotNull final String uuid) {
        if(!map.get(uuid).getUserId().equals(userId)) return null;
        return map.get(uuid);
    }

    @Override
    public List<Session> findAllByUserId(String userId) {
        //TODO
        return null;
    }

    @Override
    public Collection<Session> findAll() {
        //TODO
        return null;
    }
}
