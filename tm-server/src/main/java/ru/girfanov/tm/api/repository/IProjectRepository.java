package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Project;

import java.util.List;

public interface IProjectRepository extends Repository<Project> {
    List<Project> findAllSortedByValue(String userId, String value);
}
