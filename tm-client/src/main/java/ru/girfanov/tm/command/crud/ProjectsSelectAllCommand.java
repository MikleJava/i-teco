package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.Collection;
import java.util.Objects;

@Component
public final class ProjectsSelectAllCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-sap";

    @Getter @NotNull private final String description = "select all projects";

    @Autowired
    private ProjectEndPoint projectEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        Collection<ProjectDto> projects = null;
        System.out.print("Sort data? : ");
        String sort = scanner.next();
        if("n".equals(sort)) {
            projects = projectEndPoint.findAllProjects(sessionDto);
        }
        if("y".equals(sort)) {
            System.out.println("Choose value to sort :");
            for (int i = 0; i < 3; i++) {
                System.out.println(i + ") " + sortValue[i]);
            }
            final int index = scanner.nextInt();
            projects = projectEndPoint.findAllProjectsSortedByValue(sessionDto, sortValue[index]);
//            projects = projectEndPoint.findAllProjects(sessionDto);
        }
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tuser_id\t|\tdate_start\t|\tdate_end\t|\tstatus");
        System.out.println("____________________________________________________________________________________________________________________________________");
        for (ProjectDto project : Objects.requireNonNull(projects)) {
            System.out.println("\t" + project.getId() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getUserId() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd() + "\t|\t" + project.getStatus().name());
        }
    }
}
