package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends Service<Task> {
    Collection<Task> findAllTasksByProjectId(String projectId, String userId);
    void removeAllTasksByProjectId(String projectId, String userId);
    Collection<Task> findAllTasksByUserId(String userId);
    void removeAllTasksByUserId(String userId);
}
