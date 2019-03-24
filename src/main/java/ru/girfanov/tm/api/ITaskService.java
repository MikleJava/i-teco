package ru.girfanov.tm.api;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends Service<Task> {
    Collection<Task> findAllTasksByProjectId(String projectUuid);
}
