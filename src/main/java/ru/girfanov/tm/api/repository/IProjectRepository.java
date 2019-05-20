package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;

import java.util.List;

public interface IProjectRepository extends Repository<Project> {
    List<Project> findAllByUser(User user);
    void removeAllByUser(User user);
}
