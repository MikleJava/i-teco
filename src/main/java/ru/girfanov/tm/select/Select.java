package ru.girfanov.tm.select;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Select {

    private Scanner scanner = new Scanner(System.in);

    public void selectProjectById(List<Project> projects) {
        try {
            System.out.print("input project id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdate_start\t|\tdate_end");
            System.out.println("___________________________________________________");
            for (Project project : projects) {
                if (project.getId() == id) {System.out.println("\t" + project.getId() + "\t|\t" + project.getName() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void selectTaskById(List<Task> tasks) {
        try {
            System.out.print("input task id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("____________________________________________________________________");
            for (Task task : tasks) {
                if (task.getId() == id) {System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" +task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void selectAllProjects(List<Project> projects) {
        System.out.println("\tid\t|\tname\t|\tdate_start\t|\tdate_end");
        System.out.println("___________________________________________________");
        for (Project project : projects) {
            System.out.println("\t" + project.getId() + "\t|\t" + project.getName() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }

    public void selectAllTasks(List<Task> tasks) {
        System.out.println("\tid\t|\tname\t|\tproject_id\t|\tdate_start\t|\tdate_end");
        System.out.println("____________________________________________________________________");
        for (Task task : tasks) {
            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }
}
