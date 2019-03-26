package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public final class TaskService extends AbstractService<Task> implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final Repository<Task> repository, final ITaskRepository taskRepository) {
        super(repository);
        this.taskRepository = taskRepository;
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
        return taskRepository.findAllTasksByProjectId(projectUuid);
    }

    @Override
    public void removeAllTasksByProjectId(final String projectId) {
        if(projectId == null || projectId.isEmpty()) { return; }
        taskRepository.removeAllTasksByProjectId(projectId);
    }

    @Override
    public Collection<Task> findAllTasksByUserId(final String userId) {
        if(userId == null || userId.isEmpty()) { return null; }
        return taskRepository.findAllTasksByUserId(userId);
    }

    @Override
    public void removeAllTasksByUserId(final String userId) {
        if(userId == null || userId.isEmpty()) { return; }
        taskRepository.removeAllTasksByUserId(userId);
    }
}
