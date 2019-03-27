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
public final class TaskSelectCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-stbi";

    @NotNull
    private final String description = "select task by id";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available tasks : ");
            final List<Task> tasks = new ArrayList<>(serviceLocator.getTaskService().findAllTasksByUserId(params[0]));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________________________");
            final Task task = serviceLocator.getTaskService().findOne(tasks.get(id).getUuid(), params[0]);
            System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
