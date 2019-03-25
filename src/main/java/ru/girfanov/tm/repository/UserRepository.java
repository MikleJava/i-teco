package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements IUserRepository {

    private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(User entity) {
        userMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(String uuid, String name) {
        userMap.merge(uuid, userMap.get(uuid).setLogin(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void mergeEntityPassword(String uuid, String newPassword) {
        userMap.merge(uuid, userMap.get(uuid).setPassword(newPassword), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(String uuid) {
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
    public User findEntityById(String uuid) {
        User user = null;
        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                user = entry.getValue();
            }
        }
        return user;
    }

    @Override
    public User findOneEntityByLoginAndPassword(String login, String password) {
        User user = null;
        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            if(login.equals(entry.getValue().getLogin()) && password.equals(entry.getValue().getPassword())) {
                user = entry.getValue();
            }
        }
        return user;
    }
}
