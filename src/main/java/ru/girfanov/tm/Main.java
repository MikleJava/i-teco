package ru.girfanov.tm;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = null;
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
        while (!"--exit".equals(command)) {
            command = scanner.nextLine();
            if(command.equals("--help")) {
                System.out.println("-cp\t create project \n" +
                        "-ct\t create task \n" +
                        "-ep\t edit project \n" +
                        "-et\t edit task \n" +
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
            else if(command.equals("-ep")) {
                try {
                    System.out.print("input project id which you want to edit : ");
                    int id = scanner.nextInt();
                    for(Project project : projects) {
                        if(project.getId() == id) {
                            System.out.print("input new project name : ");
                            String name = scanner.next();
                            project.setName(name);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-et")) {
                try {
                    System.out.print("input task id which you want to edit : ");
                    int id = scanner.nextInt();
                    for(Task task : tasks) {
                        if(task.getId() == id) {
                            System.out.print("input new task name : ");
                            String name = scanner.next();
                            task.setName(name);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-dp")) {
                try {
                    System.out.print("input project id which you want to delete : ");
                    int id = scanner.nextInt();
                    for(Project project : projects) {
                        if(project.getId() == id) {
                            projects.remove(project);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-dt")) {
                try {
                    System.out.print("input task id which you want to delete : ");
                    int id = scanner.nextInt();
                    for(Task task : tasks) {
                        if(task.getId() == id) {
                            tasks.remove(id);
                        }
                    }
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
                    for (Project project : projects) {
                        if (project.getId() == id) {
                            System.out.println("\t" + project.getId() + "\t|\t" + project.getName());
                        }
                    }
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
                    for (Task task : tasks) {
                        if (task.getId() == id) {
                            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" +task.getProjectID());
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect data");
                }
            }
            else if(command.equals("-sap")) {
                System.out.println("\tid\t|\tname");
                System.out.println("____________________");
                for (Project project : projects) {
                    System.out.println("\t" + project.getId() + "\t|\t" + project.getName());
                }
            }
            else if(command.equals("-sat")) {
                System.out.println("\tid\t|\tname\t|\tproject_id");
                System.out.println("__________________________________");
                for (Task task : tasks) {
                    System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" +task.getProjectID());
                }
            }
        }
    }
}
