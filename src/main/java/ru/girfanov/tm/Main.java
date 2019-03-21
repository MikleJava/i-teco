package ru.girfanov.tm;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;
import ru.girfanov.tm.select.Select;
import ru.girfanov.tm.update.Update;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Select select = new Select();
        Update update = new Update();
        String command = null;
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
        while (!"--exit".equals(command)) {
            command = scanner.nextLine();
            if(command.equals("--help")) {
                System.out.println("-cp\t create project \n" +
                        "-ct\t create task \n" +
                        "-up\t update project \n" +
                        "-ut\t update task \n" +
                        "-dp\t delete project \n" +
                        "-dt\t delete task \n" +
                        "-spbi\t select project by id \n" +
                        "-stbi\t select task by id \n" +
                        "-sap\t select all projects \n" +
                        "-sat\t select all tasks");
            }
            if(command.equals("-cp")) {
                try {
                    System.out.print("input project id : ");
                    int id = scanner.nextInt();
                    System.out.print("input project name : ");
                    String name = scanner.next();
                    projects.add(new Project(id, name));
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-ct")) {
                try {
                    System.out.print("input task id : ");
                    int id = scanner.nextInt();
                    System.out.print("input task name : ");
                    String name = scanner.next();
                    System.out.print("input project id : ");
                    int projectID = scanner.nextInt();
                    tasks.add(new Task(id, name, projectID));
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-up")) {
                try {
                    System.out.print("input project id which you want to update : ");
                    int id = scanner.nextInt();
                    System.out.print("input new project name : ");
                    String name = scanner.next();
                    update.updateProject(projects, id, name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-ut")) {
                try {
                    System.out.print("input task id which you want to update : ");
                    int id = scanner.nextInt();
                    System.out.print("input new task name : ");
                    String name = scanner.next();
                    update.updateTask(tasks, id, name);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-dp")) {
                try {
                    System.out.print("input project id which you want to delete : ");
                    int id = scanner.nextInt();
                    projects.removeIf(project -> project.getId() == id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-dt")) {
                try {
                    System.out.print("input task id which you want to delete : ");
                    int id = scanner.nextInt();
                    tasks.removeIf(task -> task.getId() == id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-spbi")) {
                try {
                    System.out.print("input project id : ");
                    int id = scanner.nextInt();
                    System.out.println("\tid\t|\tname");
                    System.out.println("____________________");
                    select.selectProjectById(projects, id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-stbi")) {
                try {
                    System.out.print("input task id : ");
                    int id = scanner.nextInt();
                    System.out.println("\tid\t|\tname\t|\tproject_id");
                    System.out.println("__________________________________");
                    select.selectTaskById(tasks, id);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-sap")) {
                System.out.println("\tid\t|\tname");
                System.out.println("____________________");
                select.selectAllProjects(projects);
            }
            else if(command.equals("-sat")) {
                System.out.println("\tid\t|\tname\t|\tproject_id");
                System.out.println("__________________________________");
                select.selectAllTasks(tasks);
            }
        }
    }
}
