package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends Repository<Task> {
    List<Task> findAllTasksByProjectId(String userId, String projectId);
    void removeAllTasksByProjectId(String userId, String projectId);
    List<Task> findAllSortedByValue(String userId, String value);
}
