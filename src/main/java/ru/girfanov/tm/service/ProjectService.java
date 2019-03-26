package ru.girfanov.tm.service;

import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.repository.Repository;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;

import java.util.Collection;

public final class ProjectService extends AbstractService<Project> implements IProjectService {

    final private IProjectRepository projectRepository;
    final private ITaskRepository taskRepository;

    public ProjectService(final IProjectRepository projectRepository, final ITaskRepository taskRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void remove(final String uuid) {
        if(uuid == null || uuid.isEmpty()) { return; }
        taskRepository.removeAllTasksByProjectId(uuid);
        repository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        taskRepository.removeAllEntities();
        repository.removeAllEntities();
    }

    @Override
    public Collection<Project> findAllProjectsByUserId(final String userId) {
        if(userId == null || userId.isEmpty()) { return null; }
        return projectRepository.findAllProjectsByUserId(userId);
    }
}