package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @NonNull Connection connection;

    @NotNull private static final String TABLE = "session";

    @NotNull private static final String ID = "id";
    @NotNull private static final String TIMESTAMP = "time_stamp";
    @NotNull private static final String SIGNATURE = "signature";
    @NotNull private static final String USERID = "user_id";

    @Override
    public void persist(@NotNull final String userId, @NotNull final Session entity) {
        map.put(entity.getUuid(), entity);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Session entity) {}

    @Override
    public void remove(@NotNull final String userId, @NotNull final String uuid) {
        if(findOne(userId, uuid) == null) return;
        map.remove(uuid);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                map.remove(key);
            }
        });
    }

    @Override
    public Session findOne(@NotNull final String userId, @NotNull final String uuid) {
        if(!map.get(uuid).getUserId().equals(userId)) return null;
        return map.get(uuid);
    }

    @Override
    public List<Session> findAllByUserId(@NotNull final String userId) {
        final List<Session> sessions = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                sessions.add(value);
            }
        });
        return sessions;
    }

    @Override
    public Collection<Session> findAll() {
        return map.values();
    }
}
