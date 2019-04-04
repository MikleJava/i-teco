package ru.girfanov.tmserver.api.service;

import ru.girfanov.tmserver.entity.Project;

import java.util.List;

public interface IProjectService extends Service<Project> {
    List<Project> findAllSortedByValue(String userId, String value);
}
