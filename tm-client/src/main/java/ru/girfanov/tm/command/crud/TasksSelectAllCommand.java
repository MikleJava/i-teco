package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.Task;
import ru.girfanov.tm.endpoint.TaskEndPoint;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class TasksSelectAllCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-sat";

    @NotNull private final String description = "select all tasks";

    @Override
    public void execute(@NotNull final Session session) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("__________________________________________________________________________________________________________________________________________________________");
        final Collection<Task> tasks = taskEndPoint.findAllTasks(session);
        for (Task task : tasks) {
            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }
}
