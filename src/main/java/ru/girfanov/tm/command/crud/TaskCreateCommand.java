package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.enumeration.Status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-ct";

    @NotNull
    private final String description = "create task";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.print("input task name : ");
            final String name = scanner.next();
            System.out.print("input task description : ");
            final String description = scanner.next();
            System.out.print("input status : ");
            final String status = scanner.next();
            System.out.print("input date start : ");
            final Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            final Date dateEnd = dateFormat.parse(scanner.next());
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(serviceLocator.getProjectService().findAll(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int projectId = scanner.nextInt();
            serviceLocator.getTaskService().persist(params[0], new Task(name, description, params[0], Status.valueOf(status), dateEnd, dateEnd, projects.get(projectId).getUuid()));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}