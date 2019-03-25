package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends Repository<Task> {
    Collection<Task> findAllTasksByProjectId(String projectId);
    void removeAllTasksByProjectId(String projectId);
    Collection<Task> findAllTasksByUserId(String userId);
    void removeAllTasksByUserId(String userId);
}
