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
import java.util.InputMismatchException;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public final class ProjectDeleteCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-dp";

    @Getter @NotNull private final String description = "delete project";

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
            System.out.print("input project id which you want to delete : ");
            final int id = scanner.nextInt();
            projectEndPoint.removeProject(sessionDto, projects.get(id));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
