package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;
import javax.inject.Inject;
import javax.xml.datatype.DatatypeConfigurationException;

import static ru.girfanov.tm.util.DateConverterGregorianCalendar.convert;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

public final class ProjectCreateCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-cp";

    @Getter @NotNull private final String description = "create project";

    @Inject
    private ProjectEndPoint projectEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
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
            final ProjectDto projectDto = new ProjectDto();
            projectDto.setId(UUID.randomUUID().toString());
            projectDto.setName(name);
            projectDto.setDescription(description);
            projectDto.setStatus(Status.valueOf(status));
            projectDto.setUserId(sessionDto.getUserId());
            projectDto.setDateStart(convert(dateStart));
            projectDto.setDateEnd(convert(dateEnd));
            projectEndPoint.persistProject(sessionDto, projectDto);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
