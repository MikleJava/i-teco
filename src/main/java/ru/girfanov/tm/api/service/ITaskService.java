package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;

import java.util.List;

public interface ITaskService extends Service<Task> {
    List<Task> findAllTasksByProjectId(String userId, String projectId);
    void removeAllTasksByProjectId(String userId, String projectId);
    List<Task> findAllSortedByValue(String userId, String value);
}
