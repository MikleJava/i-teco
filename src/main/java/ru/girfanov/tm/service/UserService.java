package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;

public final class UserService extends AbstractService<User> implements IUserService {

    @NotNull
    final private IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void mergeUserPassword(@NotNull final String uuid, @NotNull final String newPassword) {
        if(uuid.isEmpty() || newPassword.isEmpty()) { return; }
        userRepository.mergeEntityPassword(uuid, newPassword);
    }

    @Override
    public void remove(@NotNull final String uuid) {
        if(uuid.isEmpty()) { return; }
        repository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        repository.removeAllEntities();
    }

    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        if(login.isEmpty() || password.isEmpty()) { return null; }
        return userRepository.findOneEntityByLoginAndPassword(login, password);
    }
}
