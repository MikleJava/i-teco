package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Transactional
@ApplicationScoped
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Inject private TaskRepository taskRepository;
    @Inject private UserRepository userRepository;

    @Override
    public void persist(@NotNull final User userId, @NotNull final Task task) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        taskRepository.persist(task);
    }

    @Override
    public void merge(@NotNull final User userId, @NotNull final Task task) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        taskRepository.merge(task);
    }

    @Override
    public void remove(@NotNull final User userId, @NotNull final Task task) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        taskRepository.remove(task);
    }

    @Override
    public void removeAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        taskRepository.removeAllByUser(userId);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final User userId, @NotNull final String taskId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return taskRepository.findOne(userId, taskId);
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return taskRepository.findAllByUser(userId);
    }

    @Nullable
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Nullable //does not work
    @Override
    public List<Task> findAllSortedByValue(@NotNull final User userId, @NotNull final String value) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return taskRepository.findAllSortedByValue(userId, value);
    }

    @Nullable
    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId) throws UserNotFoundException {
        if(projectId.isEmpty()) { return null; }
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        return taskRepository.findAllTasksByProjectId(userId, projectId);
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId) throws UserNotFoundException {
        if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
        taskRepository.removeAllTasksByProjectId(userId, projectId);
    }
}
