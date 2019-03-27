package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.Project;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class ProjectUpdateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-up";

    @NotNull
    private final String description = "update project";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(serviceLocator.getProjectService().findAllProjectsByUserId(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to update : ");
            final int id = scanner.nextInt();
            System.out.print("input new project name : ");
            final String name = scanner.next();
            serviceLocator.getProjectService().merge(projects.get(id).getUuid(), name);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
