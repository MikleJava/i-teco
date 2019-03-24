package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository implements ITaskRepository {

    private Map<String, Task> taskMap = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(Task entity) {
        taskMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(String uuid, String name) {
        taskMap.merge(uuid, taskMap.get(uuid).setName(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(String uuid) {
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
    public Task findEntityById(String uuid) {
        Task resultTask = null;
        for(Map.Entry<String, Task> entry : taskMap.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                resultTask = entry.getValue();
            }
        }
        return resultTask;
    }

    @Override
    public Collection<Task> findAllTasksByEntityId(String entityUuid) {
        Collection<Task> resultTasks = new ArrayList<>();
        taskMap.forEach((key, value) -> {
            if(value.getProjectId().equals(entityUuid)) {
                resultTasks.add(value);
            }
        });
        return resultTasks;
    }

    @Override
    public void removeAllTasksByEntityId(String entityUuid) {
        taskMap.forEach((key, value) -> {
            if (value.getProjectId().equals(entityUuid)) {
                taskMap.remove(key, value);
            }
        });
    }
}
