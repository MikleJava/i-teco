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
        em.createQuery("DELETE FROM app_task WHERE user_id = :user_id").setParameter("user_id", userId);
    }

    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) {
        return em.createQuery("SELECT t FROM app_task t WHERE t.user_id = :user_id AND t.id = :id", Task.class).setParameter("user_id", userId).setParameter("id", taskId).getSingleResult();
    }

    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findAllTasksByProjectId(String userId, String projectId) {
        return null;
    }

    @Override
    public void removeAllTasksByProjectId(String userId, String projectId) {

    }

    @Override
    public List<Task> findAllSortedByValue(String userId, String value) {
        return null;
    }
}
