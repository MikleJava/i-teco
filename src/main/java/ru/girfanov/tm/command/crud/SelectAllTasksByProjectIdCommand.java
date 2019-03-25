package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

public class SelectAllTasksByProjectIdCommand extends AbstractCrudCommand {

    private static final String name = "-satbpi";
    private static final String description = "select all tasks by project id";

    public SelectAllTasksByProjectIdCommand(Bootstrap bootstrap) {
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
    public void execute() {
        try {
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(bootstrap.projectService.findAll());
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("___________________________________________________________________________________________________");
            Collection<Task> tasks = bootstrap.taskService.findAllTasksByProjectId(projects.get(id).getUuid());
            for(Task task : tasks) {
                System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
