package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void persist(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        taskRepository.persist(task);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        taskRepository.merge(task);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        taskRepository.remove(task);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) throws UserNotFoundException {
        if (userId.isEmpty() || taskId.isEmpty()) return null;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        return taskRepository.findOne(taskId);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return null;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        taskRepository.removeAllByUserId(userId);
    }

    @Nullable
    @Override
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if (userId.isEmpty() || projectId.isEmpty()) return null;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if (userId.isEmpty() || projectId.isEmpty()) return;
        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
        taskRepository.removeAllByProjectId(projectId);
    }
}
