package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public final class TasksSelectAllCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-sat";

    @Getter @NotNull private final String description = "select all tasks";

    @Inject
    private TaskEndPoint taskEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("__________________________________________________________________________________________________________________________________________________________");
        final List<TaskDto> tasks = taskEndPoint.findAllTasks(sessionDto);
        for (TaskDto task : tasks) {
            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }
}
