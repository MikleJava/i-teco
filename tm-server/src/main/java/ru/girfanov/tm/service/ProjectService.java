package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;

import java.util.List;

@NoArgsConstructor
public final class ProjectService extends AbstractService<Project> implements IProjectService {

    private IProjectRepository projectRepository;

    private ITaskRepository taskRepository;

    private IUserRepository userRepository;

    public ProjectService(@NotNull final IUserRepository userRepository, @NotNull final IProjectRepository projectRepository, @NotNull final ITaskRepository taskRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if(userId.isEmpty() || value.isEmpty()) { return null; }
        return projectRepository.findAllSortedByValue(userId, value);
    }
}