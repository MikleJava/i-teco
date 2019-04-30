package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;
import javax.inject.Inject;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
public final class TaskUpdateCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-ut";

    @Getter @NotNull private final String description = "update task";

    @Inject
    private TaskEndPoint taskEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        try {
            System.out.println("all available tasks : ");
            final List<TaskDto> tasks = new ArrayList<>(taskEndPoint.findAllTasks(sessionDto));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getId() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id which you want to update : ");
            final int id = scanner.nextInt();
            System.out.print("input new task name : ");
            final String name = scanner.next();
            System.out.println("input new task description : ");
            final String description = scanner.next();
            TaskDto task = tasks.get(id);
            task.setName(name);
            task.setDescription(description);
            taskEndPoint.mergeTask(sessionDto, task);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
