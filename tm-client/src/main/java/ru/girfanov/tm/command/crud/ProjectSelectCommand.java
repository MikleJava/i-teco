package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Component
public final class ProjectSelectCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-spbi";

    @Getter @NotNull private final String description = "select project by id";

    @Autowired
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
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________");
            final ProjectDto project = projectEndPoint.findOneProject(sessionDto, projects.get(id).getId());
            System.out.println("\t" + project.getId() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
