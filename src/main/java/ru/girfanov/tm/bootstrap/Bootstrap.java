package ru.girfanov.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.command.*;
import ru.girfanov.tm.command.crud.*;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;

import java.util.*;

public final class Bootstrap implements ServiceLocator {
    @NotNull
    private final IProjectService projectService = new ProjectService(new ProjectRepository(), new TaskRepository());
    @NotNull
    private final ITaskService taskService = new TaskService(new TaskRepository());
    @NotNull
    private final IUserService userService = new UserService(new UserRepository());
    @NotNull
    private final Map<String, AbstractCommand<String>> mapCommands = new HashMap<>();
    @Nullable
    private User user;
    @NotNull
    private final Scanner scanner = new Scanner(System.in);
    @Nullable
    private String command = null;

    public void setUser(@Nullable final User user) {
        this.user = user;
    }

    @NotNull
    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @NotNull
    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @NotNull
    @Override
    public IUserService getUserService() {
        return userService;
    }


    public void init() {
        AbstractCommand<String> help = new HelpCommand(this);
        AbstractCommand<String> about = new AboutCommand(this);
        AbstractCommand<String> exit = new ExitCommand(this);
        AbstractCommand<String> cu = new CreateUserCommand(this);
        AbstractCommand<String> au = new AuthUserCommand(this);
        AbstractCommand<String> cp = new CreateProjectCommand(this);
        AbstractCommand<String> ct = new CreateTaskCommand(this);
        AbstractCommand<String> up = new UpdateProjectCommand(this);
        AbstractCommand<String> ut = new UpdateTaskCommand(this);
        AbstractCommand<String> dp = new DeleteProjectCommnd(this);
        AbstractCommand<String> dt = new DeleteTaskCommand(this);
        AbstractCommand<String> spbi = new SelectProjectCommand(this);
        AbstractCommand<String> stbi = new SelectTaskCommand(this);
        AbstractCommand<String> sap = new SelectAllProjectsCommand(this);
        AbstractCommand<String> sat = new SelectAllTasksCommand(this);
        AbstractCommand<String> satbpi = new SelectAllTasksByProjectIdCommand(this);
        AbstractCommand<String> esu = new EndSessionUserCommand(this);
        AbstractCommand<String> uup = new UpdateUserPasswordCommand(this);
        AbstractCommand<String> subi = new SelectUserCommand(this);
        AbstractCommand<String> sau = new SelectAllUsersCommand(this);

        mapCommands.put(help.getName(), help);
        mapCommands.put(about.getName(), about);
        mapCommands.put(exit.getName(), exit);
        mapCommands.put(cp.getName(), cp);
        mapCommands.put(ct.getName(), ct);
        mapCommands.put(up.getName(), up);
        mapCommands.put(ut.getName(), ut);
        mapCommands.put(dp.getName(), dp);
        mapCommands.put(dt.getName(), dt);
        mapCommands.put(spbi.getName(), spbi);
        mapCommands.put(stbi.getName(), stbi);
        mapCommands.put(sap.getName(), sap);
        mapCommands.put(sat.getName(), sat);
        mapCommands.put(satbpi.getName(), satbpi);
        mapCommands.put(cu.getName(), cu);
        mapCommands.put(au.getName(), au);
        mapCommands.put(esu.getName(), esu);
        mapCommands.put(uup.getName(), uup);
        mapCommands.put(subi.getName(), subi);
        mapCommands.put(sau.getName(), sau);

        while (!exit.getName().equals(command)) {
            command = scanner.nextLine();
            if(mapCommands.containsKey(command)) {
                if(mapCommands.get(command).isSecure()) {
                    au.execute();
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
