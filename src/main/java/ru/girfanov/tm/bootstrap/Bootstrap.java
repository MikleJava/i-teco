package ru.girfanov.tm.bootstrap;

import ru.girfanov.tm.api.*;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.info.Information;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.service.ProjectService;
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
        IProjectRepository projectRepository = new ProjectRepository();
        ITaskRepository taskRepository = new TaskRepository();
        IProjectService projectService = new ProjectService(projectRepository, taskRepository);
        ITaskService taskService = new TaskService(taskRepository);
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
                    System.out.println("all available projects : ");
                    List<Project> projects = new ArrayList<>(projectService.findAll());
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
                    }
                    System.out.print("input project id : ");
                    int projectID = scanner.nextInt();
                    System.out.print("input date start : ");
                    Date dateStart = dateFormat.parse(scanner.next());
                    System.out.print("input date end : ");
                    Date dateEnd = dateFormat.parse(scanner.next());
                    taskService.persist(new Task(name, description, projects.get(projectID).getUuid(), dateStart, dateEnd));
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                } catch (ParseException e) {
                    System.out.println("Incorrect date");
                }
            }
            if(command.equals(up)) {
                try {
                    System.out.println("all available projects : ");
                    List<Project> projects = new ArrayList<>(projectService.findAll());
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
                    }
                    System.out.print("input project id which you want to update : ");
                    int id = scanner.nextInt();
                    System.out.print("input new project name : ");
                    String name = scanner.next();
                    projectService.merge(projects.get(id).getUuid(), name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(ut)) {
                try {
                    System.out.println("all available tasks : ");
                    List<Task> tasks = new ArrayList<>(taskService.findAll());
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
                    }
                    System.out.print("input task id which you want to update : ");
                    int id = scanner.nextInt();
                    System.out.print("input new task name : ");
                    String name = scanner.next();
                    taskService.merge(tasks.get(id).getUuid(), name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(dp)) {
                try {
                    System.out.println("all available projects : ");
                    List<Project> projects = new ArrayList<>(projectService.findAll());
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
                    }
                    System.out.print("input project id which you want to delete : ");
                    int id = scanner.nextInt();
                    projectService.remove(projects.get(id).getUuid());
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(dt)) {
                try {
                    System.out.println("all available tasks : ");
                    List<Task> tasks = new ArrayList<>(taskService.findAll());
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
                    }
                    System.out.print("input task id which you want to delete : ");
                    int id = scanner.nextInt();
                    taskService.remove(tasks.get(id).getUuid());
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(spbi)) {
                try {
                    System.out.println("all available projects : ");
                    List<Project> projects = new ArrayList<>(projectService.findAll());
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
                    }
                    System.out.print("input project id : ");
                    int id = scanner.nextInt();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
                    System.out.println("_______________________________________________________________________________");
                    Project project = projectService.findOne(projects.get(id).getUuid());
                    System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            if(command.equals(stbi)) {
                try {
                    System.out.println("all available tasks : ");
                    List<Task> tasks = new ArrayList<>(taskService.findAll());
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + ") " + tasks.get(i).getUuid() + " | " + tasks.get(i).getName());
                    }
                    System.out.print("input task id : ");
                    int id = scanner.nextInt();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
                    System.out.println("_______________________________________________________________________________________________");
                    Task task = taskService.findOne(tasks.get(id).getUuid());
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
                    System.out.println("all available projects : ");
                    List<Project> projects = new ArrayList<>(projectService.findAll());
                    for (int i = 0; i < projects.size(); i++) {
                        System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
                    }
                    System.out.print("input project id : ");
                    int id = scanner.nextInt();
                    System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
                    System.out.println("___________________________________________________________________________________________________");
                    Collection<Task> tasks = taskService.findAllTasksByProjectId(projects.get(id).getUuid());
                    for(Task task : tasks) {
                        System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
        }
    }
}
