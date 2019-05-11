package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProjectRepository implements IProjectRepository {

    @NotNull
    private Map<String, Project> map = new ConcurrentHashMap<>();

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        return map.values().stream().filter(value -> value.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        map.values().removeIf(value -> value.getUserId().equals(userId));
    }

    @Override
    public void persist(@NotNull final Project project) {
        map.put(project.getId(), project);
    }

    @Override
    public void remove(@NotNull final Project project) {
        map.remove(project.getId());
    }

    @Override
    public void merge(@NotNull final Project project) {
        map.put(project.getId(), project);
    }

    @Override
    public Project findOne(String projectId) {
        return map.get(projectId);
    }

    @Override
    public Collection<Project> findAll() {
        return map.values();
    }
}
