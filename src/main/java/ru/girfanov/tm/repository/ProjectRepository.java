package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String>, IProjectRepository {

    @Override
    @Modifying
    @Query(value = "DELETE FROM Project p WHERE p.user = ?1")
    void removeAllByUser(@NotNull final User user);

    @Override
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query("SELECT p FROM Project p WHERE p.user = ?1")
    List<Project> findAllByUser(@NotNull final User user);
}
