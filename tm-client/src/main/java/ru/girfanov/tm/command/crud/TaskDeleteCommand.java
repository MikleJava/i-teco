package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.Task;
import ru.girfanov.tm.endpoint.TaskEndPoint;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class TaskDeleteCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-dt";

    @NotNull private final String description = "delete task";

    @Override
    public void execute(@NotNull final Session session) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        try {
            System.out.println("all available tasks : ");
            final List<Task> tasks = new ArrayList<>(taskEndPoint.findAllTasks(session));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to delete : ");
            final int id = scanner.nextInt();
            taskEndPoint.removeTask(session, tasks.get(id));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
