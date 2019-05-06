package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, String>, ITaskRepository {

    @Override
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.name = ?1 where t.id = ?2")
    void merge(@NotNull final String name, @NotNull final String taskId);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.user = ?1")
    void removeAllByUser(@NotNull final User userId);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.user = ?1 AND t.project = ?2")
    void removeAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId);

    @Override
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query(value = "SELECT t FROM Task t WHERE t.user = ?1")
    List<Task> findAllByUser(@NotNull final User userId);

    @Override
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query(value = "SELECT t FROM Task t WHERE t.user = ?1 AND t.project = ?2")
    List<Task> findAllTasksByProjectId(@NotNull final User userId, @NotNull final String projectId);
}
