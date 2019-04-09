package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IUserService;

@NoArgsConstructor
public final class UserService extends AbstractService<User> implements IUserService {

    private IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void mergePassword(@NotNull final String userId, @NotNull final String newPassword) {
        if (!userId.isEmpty() || !newPassword.isEmpty()) { userRepository.mergePassword(userId, newPassword); }
    }

    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        if(login.isEmpty() || password.isEmpty()) { return null; }
        return userRepository.findOneByLoginAndPassword(login, password);
    }
}
