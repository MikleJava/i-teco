package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface ITaskService extends Service<Task> {
    List<Task> findAllTasksByProjectId(User userId, String projectId) throws UserNotFoundException;
    void removeAllTasksByProjectId(User userId, String projectId) throws UserNotFoundException;
//    List<Task> findAllSortedByValue(User userId, String value) throws UserNotFoundException;
}
