package ru.girfanov.tmserver.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.api.repository.ITaskRepository;
import ru.girfanov.tmserver.api.service.ITaskService;
import ru.girfanov.tmserver.entity.Task;

import java.util.List;

@NoArgsConstructor
public final class TaskService extends AbstractService<Task> implements ITaskService {

    private ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if(userId.isEmpty() || projectId.isEmpty()) { return null; }
        return taskRepository.findAllTasksByProjectId(userId, projectId);
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if(!userId.isEmpty() || !projectId.isEmpty()) { taskRepository.removeAllTasksByProjectId(userId, projectId); }
    }

    @Override
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if(userId.isEmpty() || value.isEmpty()) { return null; }
        return taskRepository.findAllSortedByValue(userId, value);
    }
}
