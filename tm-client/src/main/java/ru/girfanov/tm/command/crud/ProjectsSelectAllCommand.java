package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.Project;
import ru.girfanov.tm.endpoint.ProjectEndPoint;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.Collection;
import java.util.Objects;

@Getter
@NoArgsConstructor
public final class ProjectsSelectAllCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-sap";

    @NotNull private final String description = "select all projects";

    @Override
    public void execute(@NotNull final Session session) {
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        Collection<Project> projects = null;
        System.out.print("Sort data? : ");
        String sort = scanner.next();
        if("n".equals(sort)) {
            projects = projectEndPoint.findAllProjects(session);
        }
        if("y".equals(sort)) {
            System.out.println("Choose value to sort :");
            for (int i = 0; i < 3; i++) {
                System.out.println(i + ") " + sortValue[i]);
            }
            final int index = scanner.nextInt();
            projects = projectEndPoint.findAllProjectsSortedByValue(session, sortValue[index]);
        }
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("____________________________________________________________________________________________________________________________________");
        for (Project project : Objects.requireNonNull(projects)) {
            System.out.println("\t" + project.getId() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getUserId() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }
}
