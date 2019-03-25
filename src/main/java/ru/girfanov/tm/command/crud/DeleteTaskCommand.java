package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DeleteTaskCommand extends AbstractCrudCommand {

    private static final String name = "-dt";
    private static final String description = "delete task";
    public DeleteTaskCommand(Bootstrap bootstrap) {
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
        try {
            System.out.println("all available tasks : ");
            List<Task> tasks = new ArrayList<>(bootstrap.taskService.findAllTasksByUserId(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to delete : ");
            int id = scanner.nextInt();
            bootstrap.taskService.remove(tasks.get(id).getUuid());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
