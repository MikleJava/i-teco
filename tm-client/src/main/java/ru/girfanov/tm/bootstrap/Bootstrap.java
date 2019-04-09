package ru.girfanov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.command.system.UserAuthCommand;
import ru.girfanov.tm.exception.AlreadyExistException;
import ru.girfanov.tm.endpoint.*;

import java.util.HashMap;
import java.util.Map;

import static ru.girfanov.tm.util.Terminal.*;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @Getter @NotNull private ProjectEndPoint projectEndPoint = new ProjectEndPointService().getProjectEndPointPort();
    @Getter @NotNull private TaskEndPoint taskEndPoint = new TaskEndPointService().getTaskEndPointPort();
    @Getter @NotNull private UserEndPoint userEndPoint = new UserEndPointService().getUserEndPointPort();
    @Getter @NotNull private DataDomainEndPoint dataDomainEndPoint = new DataDomainEndPointService().getDataDomainEndPointPort();
    @Getter @NotNull private SessionEndPoint sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();

    @NotNull private final Map<String, AbstractSystemCommand<String>> mapCommands = new HashMap<>();

    @Nullable private String command = null;

    @Override
    public void registerCommand(@NotNull final Class clazz) {
        try {
            AbstractSystemCommand<String> command = (AbstractSystemCommand<String>) clazz.newInstance();
            command.setServiceLocator(this);
            if(mapCommands.containsKey(command.getName())) throw new AlreadyExistException("Command " + command.getName() + " already exist");
            mapCommands.put(command.getName(), command);
        } catch (InstantiationException | IllegalAccessException  | ClassCastException e) {
            System.out.println("Does not correct command");
        }
    }

    @Setter @Nullable private Session session;

    @Override
    public void init(@NotNull final Class [] commandClasses) {
        for(Class clazz : commandClasses) {
            registerCommand(clazz);
        }

        while(true) {
            command = scanner.nextLine();
            if(mapCommands.containsKey(command)) {
                if(mapCommands.get(command).isSecure()) {
                    mapCommands.get(new UserAuthCommand().getName()).execute(session);
                    if(session != null) {
                        mapCommands.get(command).execute(session);
                    }
                } else {
                    try {
                        mapCommands.get(command).execute(session);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
