package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends Service<Task> {
    Collection<Task> findAllTasksByProjectId(String userId, String projectId);
    void removeAllTasksByProjectId(String userId, String projectId);
}
