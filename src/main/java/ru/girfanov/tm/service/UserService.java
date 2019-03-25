package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;

import java.util.Collection;

public class UserService implements IUserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void persist(User entity) {
        if(entity == null) { return;  }
        repository.persistEntity(entity);
    }

    @Override
    public void merge(String uuid, String name) {
        if(uuid == null || name == null || uuid.isEmpty() || name.isEmpty()) { return; }
        repository.mergeEntityName(uuid, name);
    }

    @Override
    public void mergeUserPassword(String uuid, String newPassword) {
        if(uuid == null || newPassword == null || uuid.isEmpty() || newPassword.isEmpty()) { return; }
        repository.mergeEntityPassword(uuid, newPassword);
    }

    @Override
    public void remove(String uuid) {
        if(uuid == null || uuid.isEmpty()) { return; }
        repository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        repository.removeAllEntities();
    }

    @Override
    public Collection<User> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public User findOne(String uuid) {
        if(uuid == null || uuid.isEmpty()) { return null; }
        return repository.findEntityById(uuid);
    }

    @Override
    public User findOneByLoginAndPassword(String login, String password) {
        if(login == null || password == null || login.isEmpty() || password.isEmpty()) { return null; }
        return repository.findOneEntityByLoginAndPassword(login, password);
    }
}
