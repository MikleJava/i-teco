package ru.girfanov.tmserver.api.repository;

import ru.girfanov.tmserver.entity.Task;

import java.util.List;

public interface ITaskRepository extends Repository<Task> {
    List<Task> findAllTasksByProjectId(String userId, String projectId);
    void removeAllTasksByProjectId(String userId, String projectId);
    List<Task> findAllSortedByValue(String userId, String value);
}
