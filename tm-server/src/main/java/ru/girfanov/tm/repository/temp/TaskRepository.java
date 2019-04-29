package ru.girfanov.tm.repository.temp;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

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
    public void removeAllByUser(@NotNull final User userId) {
        em.createQuery("DELETE FROM Task WHERE user = :user_id").setParameter("user_id", userId);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final User userId, @NotNull final String taskId) {
        final List<Task> tasks = em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id AND t.id = :id", Task.class).setParameter("user_id", userId).setParameter("id", taskId).getResultList();
        for(Task task : tasks) {
            if(task != null) return task;
        }
        return null;
    }

    @Override
    public List<Task> findAllByUser(@NotNull final User userId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id", Task.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user_id AND t.project = :project_id", Task.class).setParameter("user_id", userId).setParameter("project_id", projectId).getResultList();
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId) {
        em.createQuery("DELETE FROM Task WHERE user = :user_id AND project = :project_id").setParameter("user_id", userId).setParameter("project_id", projectId);
    }

    @Override
    public List<Task> findAllSortedByValue(User userId, String value) {
        return em.createQuery("SELECT t FROM Task t WHERE user = :user_id ORDER BY t." + value, Task.class).setParameter("user_id", userId).getResultList();
    }
}
