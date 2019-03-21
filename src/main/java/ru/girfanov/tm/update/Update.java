package ru.girfanov.tm.update;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Update {

    private Scanner scanner = new Scanner(System.in);

    public void updateProject(List<Project> projects) {
        try {
            System.out.print("input project id which you want to update : ");
            int id = scanner.nextInt();
            System.out.print("input new project name : ");
            String name = scanner.next();
            for(Project project : projects) {
                if(project.getId() == id) {project.setName(name);}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void updateTask(List<Task> tasks) {
        try {
            System.out.print("input task id which you want to update : ");
            int id = scanner.nextInt();
            System.out.print("input new task name : ");
            String name = scanner.next();
            for(Task task : tasks) {
                if(task.getId() == id) {task.setName(name);}
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
