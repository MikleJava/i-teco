package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void removeAll(@NotNull final String userId) {
        //if(map.containsKey(userId)) {
            map.forEach((key, value) -> {
                if(value.getUserId().equals(userId)) {
                    map.remove(key);
                }
            });
        //}
    }

    @Override
    public Collection<Task> findAll(@NotNull final String userId) {
        //if(!map.containsKey(userId)) { return null; }
        final Collection<Task> tasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                tasks.add(value);
            }
        });
        return tasks;
    }

    @Override
    public Collection<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        //if(!map.containsKey(userId)) { return  null; }
        final Collection<Task> tasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId) && value.getProjectId().equals(projectId)) {
                tasks.add(value);
            }
        });
        return tasks;
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        //if(!map.containsKey(userId)) { return; }
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId) && value.getProjectId().equals(projectId)) {
                map.remove(key);
            }
        });
    }
}
