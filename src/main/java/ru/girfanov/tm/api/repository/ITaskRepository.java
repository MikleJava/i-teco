package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import java.util.List;

public interface ITaskRepository extends Repository<Task> {
    List<Task> findAllByUser(User user);
    void removeAllByUser(User user);
//    List<Task> findAllByProject(User user, Project project);
//    void removeAllByProject(User user, Project project);
}
