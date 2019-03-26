package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public final class CreateTaskCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-ct";
    @NotNull
    private static final String description = "create task";

    public CreateTaskCommand(@NotNull final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.print("input task name : ");
            String name = scanner.next();
            System.out.print("input task description : ");
            String description = scanner.next();
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(serviceLocator.getProjectService().findAll());
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            int projectID = scanner.nextInt();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            serviceLocator.getTaskService().persist(new Task(name, description, projects.get(projectID).getUuid(), params[0], dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}
