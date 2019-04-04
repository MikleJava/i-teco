package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.Task;

import java.util.List;

public interface ITaskService extends Service<Task> {
    List<Task> findAllTasksByProjectId(String userId, String projectId);
    void removeAllTasksByProjectId(String userId, String projectId);
    List<Task> findAllSortedByValue(String userId, String value);
}
