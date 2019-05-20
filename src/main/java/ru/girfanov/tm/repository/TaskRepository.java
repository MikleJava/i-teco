package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, String>, ITaskRepository {

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query(value = "SELECT t FROM Task t WHERE t.user = ?1")
    List<Task> findAllByUser(@NotNull final User user);

    @Override
    @Modifying
    @Query("DELETE FROM Task t WHERE t.user = ?1")
    void removeAllByUser(@NotNull final User user);

//    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
//    @Query(value = "SELECT t FROM Task t WHERE t.user = ?1 AND t.project = ?2")
//    List<Task> findAllByProject(@NotNull final User user, @NotNull final Project project);
//
//    @Override
//    @Modifying
//    @Query("DELETE FROM Task t WHERE t.user = ?1 AND t.project = ?2")
//    void removeAllByProject(@NotNull final User user, @NotNull final Project project);
}
