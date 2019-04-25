package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class ProjectUpdateCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-up";

    @NotNull private final String description = "update project";

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.println("all available projects : ");
            final List<ProjectDto> projects = new ArrayList<>(projectEndPoint.findAllProjects(sessionDto));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getId() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to update : ");
            final int id = scanner.nextInt();
            final ProjectDto project = projects.get(id);
            System.out.print("input new project name : ");
            final String name = scanner.next();
            System.out.println("input new project description : ");
            final String description = scanner.next();
            project.setName(name);
            project.setDescription(description);
            projectEndPoint.mergeProject(sessionDto, project);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
