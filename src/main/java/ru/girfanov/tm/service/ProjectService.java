package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public void persist(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void remove(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public void merge(@NotNull final String userId, @NotNull final Project project) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        projectRepository.save(project);
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        final Optional<Project> project = projectRepository.findById(projectId);
        return project.orElse(null);
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return null;
        final Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) throw new UserNotFoundException("User not found");
        return projectRepository.findAllByUser(user.get());
    }

    @Override
    @Transactional
    public void removeAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        final Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) throw new UserNotFoundException("User not found");
        projectRepository.removeAllByUser(user.get());
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }
}
