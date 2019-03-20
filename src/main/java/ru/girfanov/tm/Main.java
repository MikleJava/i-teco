package ru.girfanov.tm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = null;
        while (!"exit".equals(command)) {
            command = scanner.nextLine();
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
            else if(command.equals("-spbi")) {
                System.out.print("input project id : ");
                int id = scanner.nextInt();
                System.out.println("\tid\t|\tname");
                System.out.println("____________________");
                for (Project project : projects) {
                    System.out.println("\t" + project.getId() + "\t|\t" + project.getName());
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
