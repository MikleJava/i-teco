package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.List;

public interface IProjectRepository extends Repository<Project> {
    List<Project> findAllByUserId(String userId) throws UserNotFoundException;
    void removeAllByUserId(String userId) throws UserNotFoundException;
}
