package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;

@NoArgsConstructor
public final class TaskService extends AbstractService<Task> implements ITaskService {

    private ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
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
    public Collection<Task> findAllTasksByProjectId(@NotNull final String projectUuid) {
        if(projectUuid.isEmpty()) { return null; }
        return taskRepository.findAllTasksByProjectId(projectUuid);
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String projectId) {
        if(projectId.isEmpty()) { return; }
        taskRepository.removeAllTasksByProjectId(projectId);
    }

    @Override
    public Collection<Task> findAllTasksByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        return taskRepository.findAllTasksByUserId(userId);
    }

    @Override
    public void removeAllTasksByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        taskRepository.removeAllTasksByUserId(userId);
    }
}
