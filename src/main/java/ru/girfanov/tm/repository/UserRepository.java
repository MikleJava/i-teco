package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UserRepository implements IUserRepository {

    final private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(final User entity) {
        userMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(final String uuid, final String name) {
        userMap.merge(uuid, userMap.get(uuid).setLogin(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void mergeEntityPassword(final String uuid, final String newPassword) {
        userMap.merge(uuid, userMap.get(uuid).setPassword(newPassword), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(final String uuid) {
        userMap.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        userMap.clear();
    }

    @Override
    public Collection<User> findAllEntities() {
        return userMap.values();
    }

    @Override
    public User findEntityById(final String uuid) {
        User user = null;
        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                user = entry.getValue();
            }
        }
        return user;
    }

    @Override
    public User findOneEntityByLoginAndPassword(final String login, final String password) {
        User user = null;
        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            if(login.equals(entry.getValue().getLogin()) && password.equals(entry.getValue().getPassword())) {
                user = entry.getValue();
            }
        }
        return user;
    }
}
