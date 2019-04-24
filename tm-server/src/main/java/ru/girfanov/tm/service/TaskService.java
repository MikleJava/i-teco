package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class TaskService implements ITaskService {

    @NonNull private EntityManagerFactory entityManagerFactory;

    @Override
    public void persist(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            taskRepository.persist(task);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            taskRepository.merge(task);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Task task) throws UserNotFoundException {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            taskRepository.remove(task);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            taskRepository.removeAllByUserId(userId);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) throws UserNotFoundException {
        if (userId.isEmpty() || taskId.isEmpty()) { return null; }
        Task task = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            task = taskRepository.findOne(userId, taskId);
        } finally {
            em.close();
        }
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) throws UserNotFoundException {
        if(userId.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            tasks = taskRepository.findAllByUserId(userId);
        } finally {
            em.close();
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAll() {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        List<Task> tasks = taskRepository.findAll();
        em.close();
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) throws UserNotFoundException {
        if (userId.isEmpty() || value.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            tasks = taskRepository.findAllSortedByValue(userId, value);
        } finally {
            em.close();
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if(userId.isEmpty() || projectId.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            tasks = taskRepository.findAllTasksByProjectId(userId, projectId);
        } finally {
            em.close();
        }
        return tasks;
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) throws UserNotFoundException {
        if (userId.isEmpty() || projectId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId) == null) throw new UserNotFoundException("user not found");
            taskRepository.removeAllTasksByProjectId(userId, projectId);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
