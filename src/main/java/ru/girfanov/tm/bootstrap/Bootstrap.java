package ru.girfanov.tm.bootstrap;

import ru.girfanov.tm.api.*;
import ru.girfanov.tm.command.*;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import java.util.*;

public class Bootstrap {

    private Map<String, AbstractCommand<String>> mapCommands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    //        IProjectRepository projectRepository = new ProjectRepository();
//        ITaskRepository taskRepository = new TaskRepository();
    public IProjectService projectService = new ProjectService(new ProjectRepository(), new TaskRepository());
    public ITaskService taskService = new TaskService(new TaskRepository());
    private String command = null;

    public void init() {
        AbstractCommand<String> help = new HelpCommand(this);
        AbstractCommand<String> exit = new ExitCommand(this);
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

        while (!exit.getName().equals(command)) {
            command = scanner.nextLine();
            if(mapCommands.containsKey(command)) {
                mapCommands.get(command).execute();
            }
        }
    }
}
