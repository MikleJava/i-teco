package ru.girfanov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.command.*;
import ru.girfanov.tm.command.system.UserAuthCommand;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.*;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(new ProjectRepository(), new TaskRepository());

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(new TaskRepository());

    @Getter
    @NotNull
    private final IUserService userService = new UserService(new UserRepository());

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
                    mapCommands.get(command).execute();
                }
            }
        }
    }
}
