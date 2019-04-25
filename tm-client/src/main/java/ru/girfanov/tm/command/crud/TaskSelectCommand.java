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
public final class TaskSelectCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-stbi";

    @NotNull private final String description = "select task by id";

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        try {
            System.out.println("all available tasks : ");
            final List<TaskDto> tasks = new ArrayList<>(taskEndPoint.findAllTasks(sessionDto));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ") " + tasks.get(i).getId() + " | " + tasks.get(i).getName());
            }
            System.out.print("input task id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________________________");
            final TaskDto task = taskEndPoint.findOneTask(sessionDto, tasks.get(id).getId());
            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
