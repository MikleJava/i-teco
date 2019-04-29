package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Transactional
@ApplicationScoped
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Inject private ProjectRepository projectRepository;
    @Inject private UserRepository userRepository;

    @Override
    public void persist(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        projectRepository.persist(project);
    }

    @Override
    public void merge(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        projectRepository.merge(project);
    }

    @Override
    public void remove(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        projectRepository.remove(project);
    }

    @Override
    public void removeAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        projectRepository.removeAllByUser(userId);
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final User userId, @NotNull final String projectId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return projectRepository.findOne(userId, projectId);
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return projectRepository.findAllByUser(userId);
    }

    @Nullable
    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Nullable //does not work
    @Override
    public List<Project> findAllSortedByValue(@NotNull final User userId, @NotNull final String value) throws UserNotFoundException {
        if (value.isEmpty()) { return null; }
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return projectRepository.findAllSortedByValue(userId, value);
    }
}