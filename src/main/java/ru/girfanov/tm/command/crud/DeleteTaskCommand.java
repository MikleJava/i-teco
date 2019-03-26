package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public final class DeleteTaskCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-dt";
    @NotNull
    private static final String description = "delete task";

    public DeleteTaskCommand(@NotNull final ServiceLocator serviceLocator) {
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
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available tasks : ");
            List<Task> tasks = new ArrayList<>(serviceLocator.getTaskService().findAllTasksByUserId(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to delete : ");
            int id = scanner.nextInt();
            serviceLocator.getTaskService().remove(tasks.get(id).getUuid());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
