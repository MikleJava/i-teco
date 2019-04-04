package ru.girfanov.tmserver.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.api.repository.IUserRepository;
import ru.girfanov.tmserver.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findOne(@NotNull final String userId, @NotNull final String uuid) {
        if(!map.get(userId).getUuid().equals(userId)) return null;
        return map.get(uuid);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final User entity) {
        if(findOne(userId, entity.getUuid()) == null) return;
        persist(userId, entity);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String uuid) {
        if(findOne(userId, uuid) == null) return;
        map.remove(uuid);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        map.forEach((key, value) -> {
            if(value.getUuid().equals(userId)) {
                map.remove(key);
            }
        });
    }

    @Override
    public List<User> findAllByUserId(@NotNull final String userId) {
        final List<User> users = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUuid().equals(userId)) {
                users.add(value);
            }
        });
        return users;
    }

    @Override
    public Collection<User> findAll() {
        //Сделать проверку на что, что только админ сможет использовать данный метод
        return map.values();
    }

    @Override
    public void mergePassword(@NotNull final String userId, @NotNull final String newPassword) {
        if(map.containsKey(userId)) {
            final User user = map.get(userId);
            user.setPassword(newPassword);
            merge(userId, user);
        }
    }

    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        User user = null;
        for(Map.Entry<String, User> entry : map.entrySet()) {
            if(login.equals(entry.getValue().getLogin()) && password.equals(entry.getValue().getPassword())) {
                user = entry.getValue();
            }
        }
        return user;
    }
}
