package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.Collection;

public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public void mergeEntityName(final String uuid, final String name) {
        map.merge(uuid, map.get(uuid).setName(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        map.clear();
    }

    @Override
    public Collection<Project> findAllProjectsByUserId(final String userId) {
        Collection<Project> projects = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                projects.add(value);
            }
        });
        return projects;
    }
}
