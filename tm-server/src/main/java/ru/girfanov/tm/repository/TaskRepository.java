package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public class TaskRepository implements ITaskRepository {

    @NonNull private EntityManager em;

    @Override
    public void persist(@NotNull final Task task) {
        em.persist(task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        em.merge(task);
    }

    @Override
    public void remove(@NotNull final Task task) {
        em.remove(task);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        em.createQuery("DELETE FROM Task WHERE user = :user_id").setParameter("user_id", userId);
    }

    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id AND t.id = :id", Task.class).setParameter("user_id", userId).setParameter("id", taskId).getSingleResult();
    }

    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id", Task.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id AND t.project = :project_id", Task.class).setParameter("user_id", userId).setParameter("project_id", projectId).getResultList();
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        em.createQuery("DELETE FROM Task WHERE user = :user_id AND project = :project_id").setParameter("user_id", userId).setParameter("project_id", projectId);
    }

    @Override
    public List<Task> findAllSortedByValue(String userId, String value) {
        return em.createQuery("SELECT t FROM Task t ORDER BY t." + value, Task.class).getResultList();
    }
}
