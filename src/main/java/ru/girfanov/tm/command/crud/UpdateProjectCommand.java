package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public final class UpdateProjectCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-up";
    @NotNull
    private static final String description = "update project";

    public UpdateProjectCommand(@NotNull final ServiceLocator serviceLocator) {
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
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(serviceLocator.getProjectService().findAllProjectsByUserId(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to update : ");
            int id = scanner.nextInt();
            System.out.print("input new project name : ");
            String name = scanner.next();
            serviceLocator.getProjectService().merge(projects.get(id).getUuid(), name);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
