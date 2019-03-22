package ru.girfanov.tm.service;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.Repository;
import ru.girfanov.tm.repository.TaskRepository;

import java.util.Collection;

public class TaskService implements Service<Task> {

    private Repository<Task> repository;

    public TaskService(Repository<Task> repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Task entity) {
        if(entity != null) {
            repository.persistEntity(entity);
        }
    }

    @Override
    public void merge(String uuid, String name) {
        if(!"".equals(uuid) && !"".equals(name) &&  uuid != null && name != null) {
            repository.mergeEntityName(uuid, name);
        }
    }

    @Override
    public void remove(String uuid) {
        if(!"".equals(uuid) && uuid != null) {
            repository.removeEntityById(uuid);
        }
    }

    @Override
    public void removeAll() {
        repository.removeAllEntities();
    }

    @Override
    public Collection<Task> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public Task findOne(String uuid) {
        if(!"".equals(uuid) && uuid != null) {
            return repository.findEntityById(uuid);
        }
        return null;
    }

    public Collection<Task> findAllTasksByProjectId(String projectUuid) {
        if(!"".equals(projectUuid) && projectUuid != null) {
            return ((TaskRepository)repository).findAllTasksByProjectId(projectUuid);
        }
        return null;
    }
}
