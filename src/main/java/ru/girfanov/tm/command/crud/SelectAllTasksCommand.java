package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.Task;

import java.util.Collection;

public class SelectAllTasksCommand extends AbstractCrudCommand {

    private static final String name = "-sat";
    private static final String description = "select all tasks";

    public SelectAllTasksCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(String ... params) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("__________________________________________________________________________________________________________________________________________________________");
        Collection<Task> tasks = bootstrap.taskService.findAllTasksByUserId(params[0]);
        for (Task task : tasks) {
            System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }
}
