package ru.girfanov.tm.delete;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Delete {

    private Scanner scanner = new Scanner(System.in);

    public void deleteProject(List<Project> projects) {
        try {
            System.out.print("input project id which you want to delete : ");
            int id = scanner.nextInt();
            projects.removeIf(project -> project.getId() == id);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }

    public void deleteTask(List<Task> tasks) {
        try {
            System.out.print("input task id which you want to delete : ");
            int id = scanner.nextInt();
            tasks.removeIf(task -> task.getId() == id);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
