package ru.girfanov.tm.repository;

import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskRepository implements Repository<Task>{

    private Map<String, Task> taskMap = new HashMap<>();

    @Override
    public void persistEntity(Task entity) {
        taskMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(String uuid, String name) {
        taskMap.merge(uuid, taskMap.get(uuid), (oldVal, newVal) -> newVal);
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

    public Collection<Task> findAllTasksByProjectId(String uuid) {
        Collection<Task> resultTasks = new ArrayList<>();
        for(Task task : taskMap.values()) {
            if(task.getProjectId().equals(uuid)) {
                resultTasks.add(task);
            }
        }
        return resultTasks;
    }
}
