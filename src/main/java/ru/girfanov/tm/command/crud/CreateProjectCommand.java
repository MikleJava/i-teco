package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Project;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;

public final class CreateProjectCommand extends AbstractCrudCommand {
    @NotNull
    private static final String name = "-cp";
    @NotNull
    private static final String description = "create project";

    public CreateProjectCommand(@NotNull final ServiceLocator serviceLocator) {
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
            System.out.print("input project name : ");
            String name = scanner.next();
            System.out.print("input project description : ");
            String description = scanner.next();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            serviceLocator.getProjectService().persist(new Project(name, description, params[0], dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}
