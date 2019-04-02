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
    public Project findOne(@NotNull final String userId, @NotNull final String uuid) {
        if(!map.get(uuid).getUserId().equals(userId)) return null;
        return map.get(uuid);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Project entity) {
        if(findOne(userId, entity.getUuid()) == null) return;
        persist(userId, entity);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String uuid) {
        if(findOne(userId, uuid) == null) return;
        map.remove(uuid);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                map.remove(key);
            }
        });
    }

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        final List<Project> projects = new ArrayList<>();
        map.forEach((key, value) -> {
            if(value.getUserId().equals(userId)) {
                projects.add(value);
            }
        });
        return projects;
    }

    @Override
    public Collection<Project> findAll() {
        //Сделать проверку на что, что только админ сможет использовать данный метод
        return map.values();
    }

    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        final List<Project> sortedProjects = findAllByUserId(userId);
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
