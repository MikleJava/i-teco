package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository extends Repository<Project> {
    Collection<Project> findAllProjectsByUserId(String userId);
}
