package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface ITaskService extends Service<Task> {
    void persist(String userId, Task task) throws UserNotFoundException;
    void remove(String userId, Task task) throws UserNotFoundException;
    Task findOne(String userId, String taskId) throws UserNotFoundException;
    List<Task> findAllByUserId(String userId) throws UserNotFoundException;
    void removeAllByUserId(String userId) throws UserNotFoundException;
    List<Task> findAllByProjectId(String userId, String projectId) throws UserNotFoundException;
    void removeAllByProjectId(String userId, String projectId) throws UserNotFoundException;
}
