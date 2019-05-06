package ru.girfanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.command.system.UserAuthCommand;
import ru.girfanov.tm.exception.AlreadyExistException;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.exception.IncorrectRoleException;
import ru.girfanov.tm.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static ru.girfanov.tm.util.Terminal.*;

@Component
public class Bootstrap implements ServiceLocator, ApplicationContextAware {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

    @Getter @NotNull private final Map<String, AbstractSystemCommand<String>> mapCommands = new HashMap<>();

    @Nullable private String command = null;

    @Setter @Nullable private SessionDto sessionDto;

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Override
    public void registerCommand(@NotNull final Class clazz) {
        try {
            AbstractSystemCommand<String> command = (AbstractSystemCommand<String>) ctx.getBean(clazz);
            if(mapCommands.containsKey(command.getName())) throw new AlreadyExistException("Command " + command.getName() + " already exist");
            mapCommands.put(command.getName(), command);
        } catch (AlreadyExistException | ClassCastException e) {
            System.out.println("Does not correct command");
        }
    }

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
