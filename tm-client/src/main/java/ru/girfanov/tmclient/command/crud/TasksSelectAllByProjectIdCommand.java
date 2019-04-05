package ru.girfanov.tmclient.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmclient.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.Project;
import ru.girfanov.tmserver.endpoint.ProjectEndPoint;
import ru.girfanov.tmserver.endpoint.Task;
import ru.girfanov.tmserver.endpoint.TaskEndPoint;

import static ru.girfanov.tmclient.util.Terminal.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class TasksSelectAllByProjectIdCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-satbpi";

    @NotNull
    private final String description = "select all tasks by project id";

    @Override
    public void execute(@NotNull final String ... params) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(projectEndPoint.findAllProjects(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("___________________________________________________________________________________________________");
            final Collection<Task> tasks = taskEndPoint.findAllTasksByProjectId(params[0], projects.get(id).getUuid());
            for(Task task : tasks) {
                System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
