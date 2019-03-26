package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.Map;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void mergeEntityName(final String uuid, final String name) {
        map.merge(uuid, map.get(uuid).setLogin(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void mergeEntityPassword(final String uuid, final String newPassword) {
        map.merge(uuid, map.get(uuid).setPassword(newPassword), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        map.clear();
    }

    @Override
    public User findOneEntityByLoginAndPassword(final String login, final String password) {
        User user = null;
        for(Map.Entry<String, User> entry : map.entrySet()) {
            if(login.equals(entry.getValue().getLogin()) && password.equals(entry.getValue().getPassword())) {
                user = entry.getValue();
            }
        }
        return user;
    }
}
