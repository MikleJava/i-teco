package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.PasswordHashUtil;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void persist(@NotNull final String id, @NotNull final String login, @NotNull final String password, @NotNull final Role role) {
        if(id.isEmpty() || login.isEmpty() || password.isEmpty()) { return; }
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(PasswordHashUtil.md5(password));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String password) {
        if(userId.isEmpty() || password.isEmpty()) { return; }
        final String hashPassword = PasswordHashUtil.md5(password);
        userRepository.merge(userId, hashPassword);
    }

    @Override
    public void remove(@NotNull final User user) {
        userRepository.delete(user);
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) throws UserNotFoundException {
        final Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findOneByLogin(@NotNull final String login) throws UserNotFoundException {
        final Optional<User> user = userRepository.findByLogin(login);
        return user.orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
