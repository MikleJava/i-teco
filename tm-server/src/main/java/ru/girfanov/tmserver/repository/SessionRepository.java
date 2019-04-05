package ru.girfanov.tmserver.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.api.repository.ISessionRepository;
import ru.girfanov.tmserver.entity.Session;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository{

    @Override
    public void merge(@NotNull String userId, @NotNull Session entity) {

    }

    @Override
    public void remove(@NotNull String userId, @NotNull String uuid) {

    }

    @Override
    public void removeAll(String userId) {

    }

    @Override
    public Session findOne(String userId, String uuid) {
        return null;
    }

    @Override
    public List<Session> findAllByUserId(String userId) {
        return null;
    }

    @Override
    public Collection<Session> findAll() {
        return null;
    }
}
