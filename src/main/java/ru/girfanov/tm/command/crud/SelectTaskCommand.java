package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public final class SelectTaskCommand extends AbstractCrudCommand {

    private static final String name = "-stbi";
    private static final String description = "select task by id";

    public SelectTaskCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        try {
            System.out.println("all available tasks : ");
            List<Task> tasks = new ArrayList<>(serviceLocator.getTaskService().findAllTasksByUserId(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________________________");
            Task task = serviceLocator.getTaskService().findOne(tasks.get(id).getUuid());
            System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
