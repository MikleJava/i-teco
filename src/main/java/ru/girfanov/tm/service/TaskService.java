package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public class TaskService implements ITaskService {

    private ITaskRepository repository;

    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Task entity) {
        if(entity == null) { return; }
        repository.persistEntity(entity);
    }

    @Override
    public void merge(String uuid, String name) {
        if(uuid == null || name == null || uuid.isEmpty() || name.isEmpty()) { return; }
        repository.mergeEntityName(uuid, name);
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
    public Collection<Task> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public Task findOne(String uuid) {
        if(uuid == null || uuid.isEmpty()) { return null; }
        return repository.findEntityById(uuid);
    }

    @Override
    public Collection<Task> findAllTasksByProjectId(String projectUuid) {
        if(projectUuid == null || projectUuid.isEmpty()) { return null; }
        return repository.findAllTasksByProjectId(projectUuid);
    }

    @Override
    public void removeAllTasksByProjectId(String projectId) {
        if(projectId == null || projectId.isEmpty()) { return; }
        repository.removeAllTasksByProjectId(projectId);
    }

    @Override
    public Collection<Task> findAllTasksByUserId(String userId) {
        if(userId == null || userId.isEmpty()) { return null; }
        return repository.findAllTasksByUserId(userId);
    }

    @Override
    public void removeAllTasksByUserId(String userId) {
        if(userId == null || userId.isEmpty()) { return; }
        repository.removeAllTasksByUserId(userId);
    }
}
