package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void removeAll(@NotNull final String userId) {
        //TODO
    }

    @Override
    public Collection<User> findAll(@NotNull final String userId) {
        //TODO
        return null;
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
