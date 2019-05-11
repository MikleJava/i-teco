package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends Repository<Task> {
    List<Task> findAllByUserId(String userId);
    void removeAllByUserId(String userId);
    List<Task> findAllByProjectId(String projectId);
    void removeAllByProjectId(String projectId);
}
