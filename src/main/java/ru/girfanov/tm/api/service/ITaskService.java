package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends Service<Task> {
    Collection<Task> findAllTasksByProjectId(String projectUuid);
    void removeAllTasksByProjectId(String projectId);
    Collection<Task> findAllTasksByUserId(String userId);
    void removeAllTasksByUserId(String userId);
}