package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public final class TasksSelectAllByProjectIdCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-satbpi";

    @Getter @NotNull private final String description = "select all tasks by project id";

    @Inject
    private TaskEndPoint taskEndPoint;
    @Inject
    private ProjectEndPoint projectEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        try {
            System.out.println("all available projects : ");
            final List<ProjectDto> projects = new ArrayList<>(projectEndPoint.findAllProjects(sessionDto));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getId() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("___________________________________________________________________________________________________");
            final Collection<TaskDto> tasks = taskEndPoint.findAllTasksByProjectId(sessionDto, projects.get(id).getId());
            for(TaskDto task : tasks) {
                System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
