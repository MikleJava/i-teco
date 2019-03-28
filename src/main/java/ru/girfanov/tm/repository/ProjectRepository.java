package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public void mergeEntityName(@NotNull final String uuid, @NotNull final String name) {
        final Project project = map.get(uuid);
        project.setName(name);
        map.merge(uuid, project, (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(@NotNull final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntitiesById(@NotNull final String uuid) {
        map.forEach((key, value) -> {
            if(value.getUserId().equals(uuid)) {
                map.remove(key);
            }
        });
    }
}
