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
public final class TaskDeleteCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-dt";

    @NotNull
    private final String description = "delete task";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available tasks : ");
            final List<Task> tasks = new ArrayList<>(serviceLocator.getTaskService().findAllTasksByUserId(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to delete : ");
            final int id = scanner.nextInt();
            serviceLocator.getTaskService().remove(tasks.get(id).getUuid());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
