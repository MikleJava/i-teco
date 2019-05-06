package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import java.util.List;

public interface ITaskRepository extends Repository<Task> {
    List<Task> findAllTasksByProjectId(User userId, String projectId);
    void removeAllTasksByProjectId(User userId, String projectId);
//    List<Task> findAllSortedByValue(User userId, String value);
}
