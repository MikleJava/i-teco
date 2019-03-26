package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;

public final class UserService extends AbstractService<User> implements IUserService {

    final private IUserRepository userRepository;

    public UserService(final Repository<User> repository, final IUserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public void mergeUserPassword(final String uuid, final String newPassword) {
        if(uuid == null || newPassword == null || uuid.isEmpty() || newPassword.isEmpty()) { return; }
        userRepository.mergeEntityPassword(uuid, newPassword);
    }

    @Override
    public void remove(final String uuid) {
        if(uuid == null || uuid.isEmpty()) { return; }
        repository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        repository.removeAllEntities();
    }

    @Override
    public User findOneByLoginAndPassword(final String login, final String password) {
        if(login == null || password == null || login.isEmpty() || password.isEmpty()) { return null; }
        return userRepository.findOneEntityByLoginAndPassword(login, password);
    }
}
