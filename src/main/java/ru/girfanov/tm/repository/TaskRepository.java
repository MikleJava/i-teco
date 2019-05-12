package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskRepository implements ITaskRepository {

    private static volatile TaskRepository taskRepository;

    private TaskRepository() {}

    public static TaskRepository getInstance() {
        TaskRepository instance = taskRepository;
        if(instance == null) {
            synchronized (UserRepository.class) {
                instance = taskRepository;
                if(instance == null) {
                    taskRepository = new TaskRepository();
                }
            }
        }
        return taskRepository;
    }

    @NotNull private Map<String, Task> map = new ConcurrentHashMap<>();

    @Override
    public void persist(@NotNull final Task task) {
        map.put(task.getId(), task);
    }

    @Override
    public void remove(@NotNull final Task task) {
        map.remove(task.getId());
    }

    @Override
    public void merge(@NotNull final Task task) {
        map.put(task.getId(), task);
    }

    @Override
    public Task findOne(@NotNull final String taskId) {
        return map.get(taskId);
    }

    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) {
        return map.values().stream().filter(value -> value.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        map.values().removeIf(value -> value.getUserId().equals(userId));
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull final String projectId) {
        return map.values().stream().filter(value -> value.getProjectId().equals(projectId)).collect(Collectors.toList());
    }

    @Override
    public void removeAllByProjectId(@NotNull final String projectId) {
        map.values().removeIf(value -> value.getProjectId().equals(projectId));
    }

    @Override
    public Collection<Task> findAll() {
        return map.values();
    }
}
