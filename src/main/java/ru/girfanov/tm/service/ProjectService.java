package ru.girfanov.tm.service;

import ru.girfanov.tm.api.*;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public class ProjectService implements IProjectService {

    private IProjectRepository projectRepository;
    private ITaskRepository taskRepository;

    public ProjectService(IProjectRepository projectRepository, ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void persist(Project entity) {
        if(entity == null) { return; }
        projectRepository.persistEntity(entity);
    }

    @Override
    public void merge(String uuid, String name) {
        if(uuid == null || name == null || uuid.isEmpty() || name.isEmpty()) { return; }
        projectRepository.mergeEntityName(uuid, name);
    }

    @Override
    public void remove(String uuid) {
        if(uuid == null || uuid.isEmpty()) { return; }
        taskRepository.removeAllTasksByEntityId(uuid);
        projectRepository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        taskRepository.removeAllEntities();
        projectRepository.removeAllEntities();
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAllEntities();
    }

    @Override
    public Project findOne(String uuid) {
        if(uuid == null || uuid.isEmpty()) { return null; }
        return projectRepository.findEntityById(uuid);
    }
}