package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public void persist(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void merge(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void remove(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        taskRepository.delete(task);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) throws UserNotFoundException {
        if (userId.isEmpty() || taskId.isEmpty()) return null;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        final Optional<Task> task = taskRepository.findById(taskId);
        return task.orElse(null);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return null;
        final Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) throw new UserNotFoundException("User not found");
        return taskRepository.findAllByUser(user.get());
    }

    @Override
    @Transactional
    public void removeAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if (userId.isEmpty()) return;
        final Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) throw new UserNotFoundException("User not found");
        taskRepository.removeAllByUser(user.get());
    }

//    @Nullable
//    @Override
//    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
//        if (userId.isEmpty() || projectId.isEmpty()) return null;
//        final Optional<User> user = userRepository.findById(userId);
//        if(!user.isPresent()) throw new UserNotFoundException("User not found");
//        final Optional<Project> project = userRepository.findById(userId);
//        if(!user.isPresent()) throw new UserNotFoundException("User not found");
//        return taskRepository.findAllByProject(user.get(), );
//    }
//
//    @Override
//    @Transactional
//    public void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
//        if (userId.isEmpty() || projectId.isEmpty()) return;
//        if(userRepository.findOne(userId) == null) throw new UserNotFoundException("User not found");
//        taskRepository.removeAllByProjectId(projectId);
//    }
}
