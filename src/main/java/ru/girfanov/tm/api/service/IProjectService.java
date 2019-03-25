package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public interface IProjectService extends Service<Project> {
    Collection<Project> findAllProjectsByUserId(String userId);
}
