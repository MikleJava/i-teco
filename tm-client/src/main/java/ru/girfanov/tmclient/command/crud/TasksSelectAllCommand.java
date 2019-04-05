package ru.girfanov.tmclient.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmclient.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.Task;
import ru.girfanov.tmserver.endpoint.TaskEndPoint;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class TasksSelectAllCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-sat";

    @NotNull
    private final String description = "select all tasks";

    @Override
    public void execute(final @NotNull String ... params) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("__________________________________________________________________________________________________________________________________________________________");
        final Collection<Task> tasks = taskEndPoint.findAllTasks(params[0]);
        for (Task task : tasks) {
            System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }
}
