package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.PasswordHashUtil;

import java.util.Collection;

@NoArgsConstructor
@RequiredArgsConstructor
public class UserService implements IUserService {

    @NonNull private UserRepository userRepository;

    @Override
    public void persist(@NotNull final User user) {
        user.setPassword(PasswordHashUtil.md5(user.getPassword()));
        userRepository.persist(user);
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        if(userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        return user;
    }

    @Nullable
    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        if(login.isEmpty() || password.isEmpty()) return null;
        @Nullable final User user = userRepository.findOneByLoginAndPassword(login, PasswordHashUtil.md5(password));
        return user;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final User user) throws UserNotFoundException {
        if(userId.isEmpty() || !userId.equals(user.getId())) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        user.setPassword(PasswordHashUtil.md5(user.getPassword()));
        userRepository.merge(user);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final User user) throws UserNotFoundException {
        if(userId.isEmpty() || !userId.equals(user.getId())) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        userRepository.remove(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}