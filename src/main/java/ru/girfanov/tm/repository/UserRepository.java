package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements IUserRepository {

    private static volatile UserRepository userRepository;

    private UserRepository() {}

    public static UserRepository getInstance() {
        UserRepository instance = userRepository;
        if(instance == null) {
            synchronized (UserRepository.class) {
                instance = userRepository;
                if(instance == null) {
                    userRepository = new UserRepository();
                }
            }
        }
        return userRepository;
    }

    @NotNull private Map<String, User> map = new ConcurrentHashMap<>();

    @Override
    public void persist(@NotNull final User user) {
        map.put(user.getId(), user);
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        return map.get(userId);
    }

    @Override
    public void merge(@NotNull final User user) {
        map.put(user.getId(), user);
    }

    @Override
    public void remove(@NotNull final User user) {
        map.remove(user.getId());
    }

    @Override
    public Collection<User> findAll() {
        return map.values();
    }

    @Nullable
    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        Optional<User> user = map.values().stream().filter(value -> value.getLogin().equals(login) && value.getPassword().equals(password)).findAny();
        return user.orElse(null);
    }
}
