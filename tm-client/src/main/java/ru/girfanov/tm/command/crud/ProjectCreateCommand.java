package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import javax.xml.datatype.DatatypeConfigurationException;

import static ru.girfanov.tm.util.DateConverterGregorianCalendar.convert;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

@Getter
@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-cp";

    @NotNull private final String description = "create project";

    @Override
    public void execute(@NotNull final Session session) {
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.print("input project name : ");
            final String name = scanner.next();
            System.out.print("input project description : ");
            final String description = scanner.next();
            System.out.print("input status : ");
            final String status = scanner.next();
            //System.out.print("input date start : ");
            final Date dateStart = new Date();
            //System.out.print("input date end : ");
            final Date dateEnd = new Date();
            final Project project = new Project();
            project.setUuid(UUID.randomUUID().toString());
            project.setName(name);
            project.setDescription(description);
            project.setStatus(Status.valueOf(status));
            project.setUserId(session.getUserId());
            project.setDateStart(convert(dateStart));
            project.setDateEnd(convert(dateEnd));
            projectEndPoint.persistProject(session, project);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
