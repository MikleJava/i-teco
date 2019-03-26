package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TaskRepository implements ITaskRepository {

    final private Map<String, Task> taskMap = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(final Task entity) {
        taskMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(final String uuid, final String name) {
        taskMap.merge(uuid, taskMap.get(uuid).setName(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(final String uuid) {
        taskMap.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        taskMap.clear();
    }

    @Override
    public Collection<Task> findAllEntities() {
        return taskMap.values();
    }

    @Override
    public Task findEntityById(final String uuid) {
        Task resultTask = null;
        for(Map.Entry<String, Task> entry : taskMap.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                resultTask = entry.getValue();
            }
        }
        return resultTask;
    }

    @Override
    public Collection<Task> findAllTasksByProjectId(final String projectId) {
        Collection<Task> resultTasks = new ArrayList<>();
        taskMap.forEach((key, value) -> {
            if(value.getProjectId().equals(projectId)) {
                resultTasks.add(value);
            }
        });
        return resultTasks;
    }

    @Override
    public void removeAllTasksByProjectId(final String projectId) {
        taskMap.forEach((key, value) -> {
            if (value.getProjectId().equals(projectId)) {
                taskMap.remove(key, value);
            }
        });
    }

    @Override
    public Collection<Task> findAllTasksByUserId(final String userId) {
        Collection<Task> resultTasks = new ArrayList<>();
        taskMap.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                resultTasks.add(value);
            }
        });
        return resultTasks;
    }

    @Override
    public void removeAllTasksByUserId(final String userId) {
        taskMap.forEach((key, value) -> {
            if (value.getUserId().equals(userId)) {
                taskMap.remove(key, value);
            }
        });
    }
}
