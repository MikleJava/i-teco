package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

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
    public void execute(@NotNull final SessionDto sessionDto) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        try {
            System.out.println("all available tasks : ");
            final List<TaskDto> tasks = new ArrayList<>(taskEndPoint.findAllTasks(sessionDto));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getId() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to delete : ");
            final int id = scanner.nextInt();
            taskEndPoint.removeTask(sessionDto, tasks.get(id));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
