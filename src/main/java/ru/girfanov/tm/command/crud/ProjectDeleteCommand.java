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
public final class ProjectDeleteCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-dp";

    @NotNull
    private final String description = "delete project";

    @Override
    public void execute(@NotNull final String ... params) {
        try {
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(serviceLocator.getProjectService().findAll(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to delete : ");
            final int id = scanner.nextInt();
            serviceLocator.getProjectService().remove(projects.get(id).getUuid(), params[0]);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
