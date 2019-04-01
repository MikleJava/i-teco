package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public void removeAll(@NotNull final String userId) {
            map.forEach((key, value) -> {
                if(value.getUserId().equals(userId)) {
                    map.remove(key);
                }
            });
    }

    @Override
    public Collection<Project> findAll(@NotNull final String userId) {
        final Collection<Project> projects = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                projects.add(value);
            }
        });
        return projects;
    }
}
