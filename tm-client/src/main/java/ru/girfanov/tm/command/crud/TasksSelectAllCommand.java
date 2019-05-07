package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;
import java.util.List;

@Component
public final class TasksSelectAllCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-sat";

    @Getter @NotNull private final String description = "select all tasks";

    @Autowired
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
