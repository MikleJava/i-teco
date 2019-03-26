package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public final class TaskService extends AbstractService<Task> implements ITaskService {

    final private ITaskRepository repository;

    public TaskService(final ITaskRepository repository) {
        this.repository = repository;
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
    public Collection<Task> findAllTasksByProjectId(final String projectUuid) {
        if(projectUuid == null || projectUuid.isEmpty()) { return null; }
        return repository.findAllTasksByProjectId(projectUuid);
    }

    @Override
    public void removeAllTasksByProjectId(final String projectId) {
        if(projectId == null || projectId.isEmpty()) { return; }
        repository.removeAllTasksByProjectId(projectId);
    }

    @Override
    public Collection<Task> findAllTasksByUserId(final String userId) {
        if(userId == null || userId.isEmpty()) { return null; }
        return repository.findAllTasksByUserId(userId);
    }

    @Override
    public void removeAllTasksByUserId(final String userId) {
        if(userId == null || userId.isEmpty()) { return; }
        repository.removeAllTasksByUserId(userId);
    }
}
