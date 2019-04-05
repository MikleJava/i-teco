package ru.girfanov.tmclient.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmclient.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.Project;
import ru.girfanov.tmserver.endpoint.ProjectEndPoint;

import static ru.girfanov.tmclient.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class ProjectSelectCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-spbi";

    @NotNull
    private final String description = "select project by id";

    @Override
    public void execute(@NotNull final String ... params) {
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(projectEndPoint.findAllProjects(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________");
            final Project project = projectEndPoint.findOneProject(params[0], projects.get(id).getUuid());
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
