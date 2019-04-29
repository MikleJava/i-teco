package ru.girfanov.tm;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.crud.*;
import ru.girfanov.tm.command.system.*;
import ru.girfanov.tm.command.data.*;

import javax.enterprise.inject.se.SeContainerInitializer;

public class ApplicationClient {
    @NotNull
    public static final Class[] commandClasses = {
        HelpCommand.class,
        AboutCommand.class,
        ExitCommand.class,

        ProjectCreateCommand.class,
        ProjectUpdateCommand.class,
        ProjectDeleteCommand.class,
        ProjectSelectCommand.class,
        ProjectsSelectAllCommand.class,

        TaskCreateCommand.class,
        TaskUpdateCommand.class,
        TaskDeleteCommand.class,
        TaskSelectCommand.class,
        TasksSelectAllCommand.class,
        TasksSelectAllByProjectIdCommand.class,

        UserCreateCommand.class,
        UserAuthCommand.class,
        UserDeleteCommand.class,
        UserUpdateCommand.class,
        UserSelectCommand.class,
        UsersSelectAllCommand.class,

        DataDomainGetByFasterInJson.class,
        DataDomainGetByFasterInXml.class,
        DataDomainGetByJaxbInJson.class,
        DataDomainGetByJaxbInXml.class,
        DataDomainGetBySerialization.class,
        DataDomainSaveByFasterInJson.class,
        DataDomainSaveByFasterInXml.class,
        DataDomainSaveByJaxbInJson.class,
        DataDomainSaveByJaxbInXml.class,
        DataDomainSaveBySerialization.class
    };

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(ApplicationClient.class)
                .initialize()
                .select(Bootstrap.class).get().init(commandClasses);
    }
}
