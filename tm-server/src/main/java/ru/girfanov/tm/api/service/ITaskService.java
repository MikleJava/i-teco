package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface ITaskService extends Service<Task> {
    List<Task> findAllTasksByProjectId(String userId, String projectId) throws UserNotFoundException;
    void removeAllTasksByProjectId(String userId, String projectId) throws UserNotFoundException;
    List<Task> findAllSortedByValue(String userId, String value) throws UserNotFoundException;
}
