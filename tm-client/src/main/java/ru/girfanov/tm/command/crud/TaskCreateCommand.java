package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import static ru.girfanov.tm.util.DateConverterGregorianCalendar.convert;
import static ru.girfanov.tm.util.Terminal.*;

@Component
public final class TaskCreateCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-ct";

    @Getter @NotNull private final String description = "create task";

    @Autowired
    private TaskEndPoint taskEndPoint;
    @Autowired
    private ProjectEndPoint projectEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        try {
            System.out.print("input task name : ");
            final String name = scanner.next();
            System.out.print("input task description : ");
            final String description = scanner.next();
            System.out.print("input status : ");
            final String status = scanner.next();
            //System.out.print("input date start : ");
            final Date dateStart = new Date();
//            System.out.print("input date end : ");
            final Date dateEnd = new Date();
            System.out.println("all available projects : ");
            final List<ProjectDto> projects = new ArrayList<>(projectEndPoint.findAllProjects(sessionDto));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getId() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int projectId = scanner.nextInt();
            final TaskDto task = new TaskDto();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(sessionDto.getUserId());
            task.setStatus(Status.valueOf(status));
            task.setDateStart(convert(dateStart));
            task.setDateEnd(convert(dateEnd));
            task.setProjectId(projects.get(projectId).getId());
            taskEndPoint.persistTask(sessionDto, task);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
