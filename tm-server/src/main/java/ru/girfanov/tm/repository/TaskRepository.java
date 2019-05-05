package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface TaskRepository extends EntityRepository<Task, String>, ITaskRepository {

    @Override
    void persist(@NotNull final Task task);

    @Override
    void merge(@NotNull final Task task);

    @Override
    void remove(@NotNull final Task task);

    @Override
    @Modifying
    @Query("DELETE FROM Task t WHERE t.user = :userId")
    void removeAllByUser(@QueryParam("userId") @NotNull final User userId);

    @Override
    @Modifying
    @Query("DELETE FROM Task t WHERE t.user = :userId AND t.project = :projectId")
    void removeAllTasksByProjectId(@QueryParam("userId") @NotNull final User userId, @QueryParam("projectId") @NotNull final String projectId);

    @Override
    @Query(value = "SELECT t FROM Task t WHERE t.user = :userId AND t.id = :taskId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")}, singleResult = SingleResultType.OPTIONAL)
    Task findOne(@QueryParam("userId") @NotNull final User userId, @QueryParam("taskId") @NotNull final String taskId);

    @Override
    @Query(value = "SELECT t FROM Task t WHERE t.user = :userId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")})
    List<Task> findAllByUser(@QueryParam("userId") @NotNull final User userId);

    @Override
    @Query(value = "SELECT t FROM Task t WHERE t.user = :userId AND t.project = :projectId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")})
    List<Task> findAllTasksByProjectId(@QueryParam("userId") @NotNull final User userId, @QueryParam("projectId") @NotNull final String projectId);
}
