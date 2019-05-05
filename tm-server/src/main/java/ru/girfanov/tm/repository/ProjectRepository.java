package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.hibernate.jpa.QueryHints;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectRepository extends EntityRepository<Project, String>, IProjectRepository {

    @Override
    void persist(@NotNull final Project project);

    @Override
    void merge(@NotNull final Project project);

    @Override
    void remove(@NotNull final Project project);

    @Override
    @Modifying
    @Query(value = "DELETE FROM Project p WHERE p.user = :userId")
    void removeAllByUser(@QueryParam("userId") @NotNull final User userId);

    @Override
    @Query(value = "SELECT p FROM Project p WHERE p.user = :userId AND p.id = :projectId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")}, singleResult = SingleResultType.OPTIONAL)
    Project findOne(@QueryParam("userId") @NotNull final User userId, @QueryParam("projectId") @NotNull final String projectId);

    @Override
    @Query(value = "SELECT p FROM Project p WHERE p.user = :userId", hints = {@QueryHint(name = QueryHints.HINT_CACHEABLE, value = "true")})
    List<Project> findAllByUser(@QueryParam("userId") @NotNull final User userId);
}
