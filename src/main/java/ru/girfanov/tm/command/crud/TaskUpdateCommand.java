package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.Task;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class TaskUpdateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-ut";
    @NotNull
    private final String description = "update task";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available tasks : ");
            final List<Task> tasks = new ArrayList<>(serviceLocator.getTaskService().findAll(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to update : ");
            final int id = scanner.nextInt();
            System.out.print("input new task name : ");
            final String name = scanner.next();
            System.out.println("input new task description : ");
            final String description = scanner.next();
            Task task = tasks.get(id);
            task.setName(name);
            task.setDescription(description);
            serviceLocator.getTaskService().merge(params[0], task);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
