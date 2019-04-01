package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.comparator.SortByEndDate;
import ru.girfanov.tm.comparator.SortByStartDate;
import ru.girfanov.tm.comparator.SortByStatus;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void removeAll(@NotNull final String userId) {
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                map.remove(key);
            }
        });
    }

    @Override
    public List<Task> findAll(@NotNull final String userId) {
        final List<Task> tasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                tasks.add(value);
            }
        });
        return tasks;
    }

    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        final List<Task> tasks = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId) && value.getProjectId().equals(projectId)) {
                tasks.add(value);
            }
        });
        return tasks;
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId) && value.getProjectId().equals(projectId)) {
                map.remove(key);
            }
        });
    }

    @Override
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        final List<Task> sortedProjects = findAll(userId);
        if("date start".equals(value)) {
            Comparator<Task> comparator = new SortByStartDate<>();
            sortedProjects.sort(comparator);
        }
        if("date end".equals(value)) {
            Comparator<Task> comparator = new SortByEndDate<>();
            sortedProjects.sort(comparator);
        }
        if("status".equals(value)) {
            Comparator<Task> comparator = new SortByStatus<>();
            sortedProjects.sort(comparator);
        }
        return sortedProjects;
    }
}
