package ru.girfanov.tm.repository;

import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectRepository implements IProjectRepository {

    private Map<String, Project> projectMap = new ConcurrentHashMap<>();

    @Override
    public void persistEntity(Project entity) {
        projectMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(String uuid, String name) {
        projectMap.merge(uuid, projectMap.get(uuid).setName(name), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(String uuid) {
        projectMap.remove(uuid);
    }

    @Override
    public void removeAllEntities() {
        projectMap.clear();
    }

    @Override
    public Collection<Project> findAllEntities() {
        return projectMap.values();
    }

    @Override
    public Project findEntityById(String uuid) {
        Project resultProject = null;
        for(Map.Entry<String, Project> entry : projectMap.entrySet()) {
            if(uuid.equals(entry.getValue().getUuid())) {
                resultProject = entry.getValue();
            }
        }
        return resultProject;
    }

    @Override
    public Collection<Project> findAllProjectsByUserId(String userId) {
        Collection<Project> projects = new ArrayList<>();
        projectMap.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                projects.add(value);
            }
        });
        return projects;
    }
}
