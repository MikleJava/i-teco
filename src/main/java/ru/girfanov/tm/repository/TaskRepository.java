package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;

public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void mergeEntityName(final String uuid, final String name) {
        map.merge(uuid, map.get(uuid).setName(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        map.clear();
    }

    @Override
    public Collection<Task> findAllTasksByProjectId(final String projectId) {
        Collection<Task> resultTasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getProjectId().equals(projectId)) {
                resultTasks.add(value);
            }
        });
        return resultTasks;
    }

    @Override
    public void removeAllTasksByProjectId(final String projectId) {
        map.forEach((key, value) -> {
            if (value.getProjectId().equals(projectId)) {
                map.remove(key, value);
            }
        });
    }

    @Override
    public Collection<Task> findAllTasksByUserId(final String userId) {
        Collection<Task> resultTasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                resultTasks.add(value);
            }
        });
        return resultTasks;
    }

    @Override
    public void removeAllTasksByUserId(final String userId) {
        map.forEach((key, value) -> {
            if (value.getUserId().equals(userId)) {
                map.remove(key, value);
            }
        });
    }
}
