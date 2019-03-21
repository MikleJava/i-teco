package ru.girfanov.tm.delete;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Delete {

    private Scanner scanner = new Scanner(System.in);

    public void deleteProject(List<Project> projects) {
        try {
            System.out.print("input project id which you want to delete : ");
            String id = scanner.next();
            projects.removeIf(project -> project.getUuid().equals(id));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void deleteTask(List<Task> tasks) {
        try {
            System.out.print("input task id which you want to delete : ");
            String id = scanner.next();
            tasks.removeIf(task -> task.getUuid().equals(id));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
