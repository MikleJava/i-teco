package ru.girfanov.tmserver.api.repository;

import ru.girfanov.tmserver.entity.Project;

import java.util.List;

public interface IProjectRepository extends Repository<Project> {
    List<Project> findAllSortedByValue(String userId, String value);
}
