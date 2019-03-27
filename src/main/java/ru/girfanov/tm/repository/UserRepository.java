package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.Map;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void mergeEntityName(@NotNull final String uuid, @NotNull final String name) {
        User user = map.get(uuid);
        user.setName(name);
        map.merge(uuid, user, (oldVal, newVal) -> newVal);
    }

    @Override
    public void mergeEntityPassword(@NotNull final String uuid, @NotNull final String newPassword) {
        User user = map.get(uuid);
        user.setPassword(newPassword);
        map.merge(uuid, user, (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(@NotNull final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        map.clear();
    }

    @Override
    public User findOneEntityByNameAndPassword(@NotNull final String name, @NotNull final String password) {
        User user = null;
        for(Map.Entry<String, User> entry : map.entrySet()) {
            if(name.equals(entry.getValue().getName()) && password.equals(entry.getValue().getPassword())) {
                user = entry.getValue();
            }
        }
        return user;
    }
}
