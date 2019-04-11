package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import static ru.girfanov.tm.util.DateConverterGregorianCalendar.*;
import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class TaskCreateCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-ct";

    @NotNull private final String description = "create task";

    @Override
    public void execute(@NotNull final Session session) {
        final TaskEndPoint taskEndPoint = serviceLocator.getTaskEndPoint();
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.print("input task name : ");
            final String name = scanner.next();
            System.out.print("input task description : ");
            final String description = scanner.next();
            System.out.print("input status : ");
            final String status = scanner.next();
            System.out.print("input date start : ");
            final Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            final Date dateEnd = dateFormat.parse(scanner.next());
            System.out.println("all available projects : ");
            final List<Project> projects = new ArrayList<>(projectEndPoint.findAllProjects(session));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            final int projectId = scanner.nextInt();
            final Task task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setUserId(session.getUserId());
            task.setStatus(status);
            task.setDateStart(convert(dateStart));
            task.setDateEnd(convert(dateEnd));
            task.setProjectId(projects.get(projectId).getUuid());
            taskEndPoint.persistTask(session, task);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}