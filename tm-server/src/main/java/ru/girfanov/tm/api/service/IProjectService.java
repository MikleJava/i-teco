package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Project;

import java.util.List;

public interface IProjectService extends Service<Project> {
    List<Project> findAllSortedByValue(String userId, String value);
}
