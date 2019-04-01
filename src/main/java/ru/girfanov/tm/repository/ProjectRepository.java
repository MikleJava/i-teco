package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.comparator.SortByEndDate;
import ru.girfanov.tm.comparator.SortByStartDate;
import ru.girfanov.tm.comparator.SortByStatus;
import ru.girfanov.tm.entity.Project;

import java.util.*;

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
    public List<Project> findAll(@NotNull final String userId) {
        final List<Project> projects = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                projects.add(value);
            }
        });
        return projects;
    }

    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        final List<Project> sortedProjects = findAll(userId);
        if("date start".equals(value)) {
            Comparator<Project> comparator = new SortByStartDate<>();
            sortedProjects.sort(comparator);
        }
        if("date end".equals(value)) {
            Comparator<Project> comparator = new SortByEndDate<>();
            sortedProjects.sort(comparator);
        }
        if("status".equals(value)) {
            Comparator<Project> comparator = new SortByStatus<>();
            sortedProjects.sort(comparator);
        }
        return sortedProjects;
    }
}
