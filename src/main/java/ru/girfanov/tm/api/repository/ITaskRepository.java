package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends Repository<Task> {
    Collection<Task> findAllTasksByEntityId(String entityUuid);
    void removeAllTasksByEntityId(String entityUuid);
}
