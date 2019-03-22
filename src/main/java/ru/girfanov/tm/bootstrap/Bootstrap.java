package ru.girfanov.tm.bootstrap;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.info.Information;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.Repository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.Service;
import ru.girfanov.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bootstrap {

    private static final String exit = "--exit";
    private static final String help = "--help";
    private static final String cp = "-cp";
    private static final String ct = "-ct";
    private static final String up = "-up";
    private static final String ut = "-ut";
    private static final String dp = "-dp";
    private static final String dt = "-dt";
    private static final String spbi = "-spbi";
    private static final String stbi = "-stbi";
    private static final String sap = "-sap";
    private static final String sat = "-sat";
    private static final String satbpi = "-satbpi";
    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    public void init() {
        Information info = new Information();
        Scanner scanner = new Scanner(System.in);
        Repository<Project> projectRepository = new ProjectRepository();
        Repository<Task> taskRepository = new TaskRepository();
        Service<Project> projectService = new ProjectService(projectRepository);
        Service<Task> taskService = new TaskService(taskRepository);
        String command = null;

        while (!exit.equals(command)) {
            command = scanner.nextLine();
            if(command.equals(help)) {info.getInfo();}
            if(command.equals(cp)) {
                try {
                    System.out.print("input project name : ");
                    String name = scanner.next();
                    System.out.print("input project description : ");
                    String description = scanner.next();
                    System.out.print("input date start : ");
                    Date dateStart = dateFormat.parse(scanner.next());
                    System.out.print("input date end : ");
                    Date dateEnd = dateFormat.parse(scanner.next());
                    projectService.persist(new Project(name, description, dateStart, dateEnd));
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                } catch (ParseException e) {
                    System.out.println("Incorrect date");
                }
            }
            if(command.equals(ct)) {
                try {
                    System.out.print("input task name : ");
                    String name = scanner.next();
                    System.out.print("input task description : ");
                    String description = scanner.next();
                    System.out.print("input project id : ");
                    String projectID = scanner.next();
                    System.out.print("input date start : ");
                    Date dateStart = dateFormat.parse(scanner.next());
                    System.out.print("input date end : ");
                    Date dateEnd = dateFormat.parse(scanner.next());
                    taskService.persist(new Task(name, description, projectID, dateStart, dateEnd));
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                } catch (ParseException e) {
                    System.out.println("Incorrect date");
                }
            }
            if(command.equals(up)) {
                try {
                    System.out.print("input project id which you want to update : ");
                    String id = scanner.next();
                    System.out.print("input new project name : ");
                    String name = scanner.next();
                    projectService.merge(id, name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(ut)) {
                try {
                    System.out.print("input task id which you want to update : ");
                    String id = scanner.next();
                    System.out.print("input new task name : ");
                    String name = scanner.next();
                    taskService.merge(id, name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(dp)) {
                try {
                    System.out.print("input project id which you want to delete : ");
                    String id = scanner.next();
                    projectService.remove(id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(dt)) {
                try {
                    System.out.print("input task id which you want to delete : ");
                    String id = scanner.next();
                    taskService.remove(id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(spbi)) {
                try {
                    System.out.print("input project id : ");
                    String id = scanner.next();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
                    System.out.println("_______________________________________________________________________________");
                    Project project = projectService.findOne(id);
                    System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(stbi)) {
                try {
                    System.out.print("input task id : ");
                    String id = scanner.next();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
                    System.out.println("_______________________________________________________________________________________________");
                    Task task = taskService.findOne(id);
                    System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(sap)) {
                System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
                System.out.println("_________________________________________________________________________________");
                Collection<Project> projects = projectService.findAll();
                for (Project project : projects) {
                    System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
                }
            }
            if(command.equals(sat)) {
                System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
                System.out.println("___________________________________________________________________________________________________");
                Collection<Task> tasks = taskService.findAll();
                for (Task task : tasks) {
                    System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
                }
            }
            if(command.equals(satbpi)) {
                try {
                    System.out.print("input project id : ");
                    String id = scanner.next();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
                    System.out.println("___________________________________________________________________________________________________");
                    Collection<Task> tasks = ((TaskService) taskService).findAllTasksByProjectId(id);
                    for(Task task : tasks) {
                        if(task.getProjectId().equals(id)) {System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());}
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
        }
    }
}
