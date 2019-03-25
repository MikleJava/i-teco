package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class UpdateTaskCommand extends AbstractCrudCommand {

    private static final String name = "-ut";
    private static final String description = "update task";

    public UpdateTaskCommand(Bootstrap bootstrap) {
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
            System.out.println("all available tasks : ");
            List<Task> tasks = new ArrayList<>(bootstrap.taskService.findAll());
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to update : ");
            int id = scanner.nextInt();
            System.out.print("input new task name : ");
            String name = scanner.next();
            bootstrap.taskService.merge(tasks.get(id).getUuid(), name);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
