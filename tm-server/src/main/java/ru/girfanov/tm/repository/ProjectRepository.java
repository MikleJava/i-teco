package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;

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
    @Query("DELETE FROM Project p WHERE p.user = :userId")
    void removeAllByUser(@QueryParam("userId") @NotNull final User userId);

    @Override
    @Query(value = "SELECT p FROM Project p WHERE p.user = :userId AND p.id = :projectId", singleResult = SingleResultType.OPTIONAL)
    Project findOne(@QueryParam("userId") @NotNull final User userId, @QueryParam("projectId") @NotNull final String projectId);

    @Override
    @Query("SELECT p FROM Project p WHERE p.user = :userId")
    List<Project> findAllByUser(@QueryParam("userId") @NotNull final User userId);
}
