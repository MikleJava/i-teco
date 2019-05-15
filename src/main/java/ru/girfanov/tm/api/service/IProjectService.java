package ru.girfanov.tm.api.service;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface IProjectService extends Service<Project> {
    void persist(String userId, Project project);
    void remove(String userId, Project project) throws UserNotFoundException;
    Project findOne(String userId, String projectId) throws UserNotFoundException;
    List<Project> findAllByUserId(String userId) throws UserNotFoundException;
    void removeAllByUserId(String userId) throws UserNotFoundException;
}
