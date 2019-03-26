package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public final class ProjectService extends AbstractService<Project> implements IProjectService {

    @NotNull
    final private IProjectRepository projectRepository;
    @NotNull
    final private ITaskRepository taskRepository;

    public ProjectService(@NotNull final IProjectRepository projectRepository, @NotNull final ITaskRepository taskRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void remove(@NotNull final String uuid) {
        if(uuid.isEmpty()) { return; }
        taskRepository.removeAllTasksByProjectId(uuid);
        repository.removeEntityById(uuid);
    }

    @Override
    public void removeAll() {
        taskRepository.removeAllEntities();
        repository.removeAllEntities();
    }

    @Override
    public Collection<Project> findAllProjectsByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        return projectRepository.findAllProjectsByUserId(userId);
    }
}