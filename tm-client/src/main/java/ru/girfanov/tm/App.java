package ru.girfanov.tm;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.crud.*;
import ru.girfanov.tm.command.system.*;
import ru.girfanov.tm.command.system.data.*;

@NoArgsConstructor
public class App {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

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
        UserEndSessionCommand.class,
        UserPasswordUpdateCommand.class,
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
        @NotNull ServiceLocator serviceLocator = new Bootstrap();
        serviceLocator.init(commandClasses);
    }
}
