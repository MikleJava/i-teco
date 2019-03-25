package ru.girfanov.tm.bootstrap;

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

public class Bootstrap {

    public IProjectService projectService = new ProjectService(new ProjectRepository(), new TaskRepository());
    public ITaskService taskService = new TaskService(new TaskRepository());
    public IUserService userService = new UserService(new UserRepository());

    private Map<String, AbstractCommand<String>> mapCommands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private String command = null;

    public void init() {
        AbstractCommand<String> help = new HelpCommand(this);
        AbstractCommand<String> exit = new ExitCommand(this);
        CreateUserCommand cu = new CreateUserCommand(this);
        AuthUserCommand au = new AuthUserCommand(this);
        AbstractCrudCommand cp = new CreateProjectCommand(this);
        AbstractCrudCommand ct = new CreateTaskCommand(this);
        AbstractCrudCommand up = new UpdateProjectCommand(this);
        AbstractCrudCommand ut = new UpdateTaskCommand(this);
        AbstractCrudCommand dp = new DeleteProjectCommnd(this);
        AbstractCrudCommand dt = new DeleteTaskCommand(this);
        AbstractCrudCommand spbi = new SelectProjectCommand(this);
        AbstractCrudCommand stbi = new SelectTaskCommand(this);
        AbstractCrudCommand sap = new SelectAllProjectsCommand(this);
        AbstractCrudCommand sat = new SelectAllTasksCommand(this);
        AbstractCrudCommand satbpi = new SelectAllTasksByProjectIdCommand(this);
        AbstractCrudCommand esu = new EndSessionUserCommand(this);
        AbstractCrudCommand uup = new UpdateUserPasswordCommand(this);
        AbstractCrudCommand subi = new SelectUserCommand(this);
        AbstractCrudCommand sau = new SelectAllUsersCommand(this);

        mapCommands.put(help.getName(), help);
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
                if (mapCommands.get(command) instanceof AbstractCrudCommand) {
                    au.execute();
                    User user = au.getAuthUser();
                    if(user == null) { System.out.println("You must be logged in"); }
                    else {mapCommands.get(command).execute();}
                } else {
                    mapCommands.get(command).execute();
                }
            }
        }
    }
}
