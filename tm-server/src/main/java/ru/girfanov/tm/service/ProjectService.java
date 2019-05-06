package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void persist(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        projectRepository.save(project);
    }

    @Override
    public void merge(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        projectRepository.merge(project.getName(), project.getId());
    }

    @Override
    public void remove(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        projectRepository.delete(project);
    }

    @Override
    public void removeAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        projectRepository.removeAllByUser(userId);
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final User userId, @NotNull final String projectId) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        return projectRepository.findById(projectId).isPresent() ? projectRepository.findById(projectId).get() : null;
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
        return projectRepository.findAllByUser(userId);
    }

    @Nullable
    @Override
    public List<Project> findAll() {
        return (List<Project>) projectRepository.findAll();
    }

//    @Nullable //does not work
//    @Override
//    public List<Project> findAllSortedByValue(@NotNull final User userId, @NotNull final String value) throws UserNotFoundException {
//        if (value.isEmpty()) { return null; }
//        if(!userRepository.findById(userId.getId()).isPresent()) throw new UserNotFoundException("user not found");
//        return projectRepository.findAllSortedByValue(userId, value);
//    }
}