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
    public void persist(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            taskRepository.persist(task);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            taskRepository.merge(task);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            taskRepository.remove(task);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            taskRepository.removeAllByUserId(userId);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) {
        if (userId.isEmpty() || taskId.isEmpty()) { return null; }
        Task task = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            task = taskRepository.findOne(userId, taskId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            tasks = taskRepository.findAllByUserId(userId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
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
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if (userId.isEmpty() || value.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            tasks = taskRepository.findAllSortedByValue(userId, value);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if(userId.isEmpty() || projectId.isEmpty()) { return null; }
        List<Task> tasks = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            tasks = taskRepository.findAllTasksByProjectId(userId, projectId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return tasks;
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if (userId.isEmpty() || projectId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final TaskRepository taskRepository = new TaskRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            taskRepository.removeAllTasksByProjectId(userId, projectId);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }
}
