package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;

import java.util.Collection;
import java.util.List;

public class ProjectService implements IProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Override
    public void persist(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        projectRepository.persist(project);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        projectRepository.remove(project);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        projectRepository.merge(project);
    }

    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if (userId.isEmpty() || projectId.isEmpty()) return null;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        return projectRepository.findOne(projectId);
    }

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return null;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        projectRepository.removeAllByUserId(userId);
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }
}
