package ru.girfanov.tm.repository;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaskRepository {

    private Map<String, Task> taskMap = new HashMap<>();

    public void persist(String uuid, Task task) {
        taskMap.put(uuid, task);
    }

    public void mergeTaskName(Task task, String name) {
        taskMap.merge(task.getUuid(), task, (oldVal, newVal) -> newVal);
    }

    public void removeTaskbyId(Task task) {
        taskMap.remove(task.getUuid(), task);
    }

    public void removeAllTasks() {
        taskMap.clear();
    }

    public Collection<Task> findAll() {
        return taskMap.values();
    }

    public Task findTaskById(Task task) {
        Task resultTask = null;
        for(Map.Entry<String, Task> entry : taskMap.entrySet()) {
            if(Objects.requireNonNull(task.getUuid()).equals(entry.getValue().getUuid())) {
                resultTask = entry.getValue();
            }
        }
        return resultTask;
    }
}
