package ru.girfanov.tmclient.bootstrap;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmclient.command.AbstractSystemCommand;
import ru.girfanov.tmclient.command.system.UserAuthCommand;

import java.util.HashMap;
import java.util.Map;

import static ru.girfanov.tmserver.util.Terminal.*;
import static ru.girfanov.tmserver.util.Terminal.scanner;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NonNull private ProjectE

    @NotNull
    private final Map<String, AbstractSystemCommand<String>> mapCommands = new HashMap<>();

    @Nullable
    private User user;

    @Nullable
    private String command = null;

    @Override
    public void setUser(@Nullable final User user) {
        this.user = user;
    }

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

    @Override
    public void init(@NotNull final Class [] commandClasses) {
        for(Class clazz : commandClasses) {
            registerCommand(clazz);
        }

        while(true) {
            command = scanner.nextLine();
            if(mapCommands.containsKey(command)) {
                if(mapCommands.get(command).isSecure()) {
                    mapCommands.get(new UserAuthCommand().getName()).execute();
                    if(user != null) {
                        mapCommands.get(command).execute(user.getUuid());
                    }
                } else {
                    try {
                        mapCommands.get(command).execute();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
