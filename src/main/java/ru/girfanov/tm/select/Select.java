package ru.girfanov.tm.select;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Select {

    private Scanner scanner = new Scanner(System.in);

    public void selectProjectById(List<Project> projects) {
        try {
            System.out.print("input project id : ");
            String id = scanner.next();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________");
            for (Project project : projects) {
                if (project.getUuid().equals(id)) {System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void selectTaskById(List<Task> tasks) {
        try {
            System.out.print("input task id : ");
            String id = scanner.next();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________________________");
            for (Task task : tasks) {
                if (task.getUuid().equals(id)) {System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void selectAllProjects(List<Project> projects) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
        System.out.println("_________________________________________________________________________________");
        for (Project project : projects) {
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }

    public void selectAllTasks(List<Task> tasks) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
        System.out.println("___________________________________________________________________________________________________");
        for (Task task : tasks) {
            System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());
        }
    }

    public void selectAllTasksByProjectId(List<Task> tasks) {
        try {
            System.out.print("input project id : ");
            String id = scanner.next();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tproject_id\t|\tdate_start\t|\tdate_end");
            System.out.println("___________________________________________________________________________________________________");
            for(Task task : tasks) {
                if(task.getProjectId().equals(id)) {System.out.println("\t" + task.getUuid() + "\t|\t" + task.getName() + "\t|\t" + task.getDescription() + "\t|\t" + task.getProjectId() + "\t|\t" + task.getDateStart() + "\t|\t" + task.getDateEnd());}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
