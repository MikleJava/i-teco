package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.Project;
import ru.girfanov.tmserver.endpoint.ProjectEndPoint;

import javax.xml.datatype.DatatypeConfigurationException;

import static ru.girfanov.tm.util.DateConverterGregorianCalendar.*;
import static ru.girfanov.tm.util.Terminal.*;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;


@Getter
@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-cp";

    @NotNull
    private final String description = "create project";

    @Override
    public void execute(@NotNull final String ... params) {
        final ProjectEndPoint projectEndPoint = serviceLocator.getProjectEndPoint();
        try {
            System.out.print("input project name : ");
            final String name = scanner.next();
            System.out.print("input project description : ");
            final String description = scanner.next();
            System.out.print("input status : ");
            final String status = scanner.next();
            System.out.print("input date start : ");
            final Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            final Date dateEnd = dateFormat.parse(scanner.next());
            final Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setUserId(params[0]);
            project.setDateStart(convert(dateStart));
            project.setDateEnd(convert(dateEnd));
            projectEndPoint.persistProject(params[0], project);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
