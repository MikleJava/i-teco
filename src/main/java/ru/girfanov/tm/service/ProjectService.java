package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

@NoArgsConstructor
public final class ProjectService extends AbstractService<Project> implements IProjectService {

    private IProjectRepository projectRepository;

    private ITaskRepository taskRepository;

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