package ru.girfanov.tm.repository;

import ru.girfanov.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProjectRepository implements Repository<Project>{

    private Map<String, Project> projectMap = new HashMap<>();

    @Override
    public void persistEntity(Project entity) {
        projectMap.put(entity.getUuid(), entity);
    }

    @Override
    public void mergeEntityName(String uuid, String name) {
        projectMap.merge(uuid, projectMap.get(uuid), (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(String uuid) {
        projectMap.remove(uuid);
    }

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
}
