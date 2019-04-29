package ru.girfanov.tm.bootstrap;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.command.system.UserAuthCommand;
import ru.girfanov.tm.exception.AlreadyExistException;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.exception.IncorrectRoleException;
import ru.girfanov.tm.exception.UserNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

import static ru.girfanov.tm.util.Terminal.*;

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

    @NotNull private final Map<String, AbstractSystemCommand<String>> mapCommands = new HashMap<>();

    @Nullable private String command = null;

    @Override
    public void registerCommand(@NotNull final Class clazz) {
        try {
            AbstractSystemCommand<String> command = (AbstractSystemCommand<String>) clazz.newInstance();
            if(mapCommands.containsKey(command.getName())) throw new AlreadyExistException("Command " + command.getName() + " already exist");
            mapCommands.put(command.getName(), command);
        } catch (InstantiationException | AlreadyExistException | IllegalAccessException  | ClassCastException e) {
            System.out.println("Does not correct command");
        }
    }

    @Setter @Nullable private SessionDto sessionDto;

    @Override
    public void init(@NotNull final Class [] commandClasses) {
        for(Class clazz : commandClasses) {
            registerCommand(clazz);
        }

        while(true) {
            command = scanner.nextLine();
            if(mapCommands.containsKey(command)) {
                if(mapCommands.get(command).isSecure()) {
                    try {
                        mapCommands.get(new UserAuthCommand().getName()).execute(sessionDto);
                    } catch (UserNotFoundException | IncorrectRoleException e) {
                        System.out.println(e.getMessage());
                    }
                    if(sessionDto != null) {
                        try {
                            mapCommands.get(command).execute(sessionDto);
                        } catch (UserNotFoundException | IncorrectRoleException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    try {
                        mapCommands.get(command).execute(sessionDto);
                    } catch (UserNotFoundException | IncorrectRoleException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
