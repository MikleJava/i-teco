package ru.girfanov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.command.*;
import ru.girfanov.tm.command.system.UserAuthCommand;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.AlreadyExistException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.DataDomainService;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.*;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @NotNull
    @Getter
    private final IProjectService projectService = new ProjectService(userRepository, projectRepository, taskRepository);

    @NotNull
    @Getter
    private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull
    @Getter
    private final IUserService userService = new UserService(userRepository);

    @NotNull
    @Getter
    private final IDataDomainService dataDomainService = new DataDomainService(projectRepository, taskRepository, userRepository);

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
