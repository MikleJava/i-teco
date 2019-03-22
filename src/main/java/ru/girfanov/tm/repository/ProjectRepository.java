package ru.girfanov.tm.repository;

import ru.girfanov.tm.entity.Project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProjectRepository {

    private Map<String, Project> projectMap = new HashMap<>();

    public void persist(String uuid, Project project) {
        projectMap.put(uuid, project);
    }

    public void mergeProjectName(Project project, String name) {
        projectMap.merge(project.getUuid(), project, (oldVal, newVal) -> newVal);
    }

    public void removeProjectbyId(Project project) {
        projectMap.remove(project.getUuid(), project);
    }

    public void removeAllProjects() {
        projectMap.clear();
    }

    public Collection<Project> findAll() {
        return projectMap.values();
    }

    public Project findProjectById(Project project) {
        Project resultProject = null;
        for(Map.Entry<String, Project> entry : projectMap.entrySet()) {
            if(Objects.requireNonNull(project.getUuid()).equals(entry.getValue().getUuid())) {
                resultProject = entry.getValue();
            }
        }
        return resultProject;
    }
}
