package ru.girfanov.tm.select;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.util.InputMismatchException;
import java.util.List;

public class Select {
    public void selectProjectById(List<Project> projects, int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                System.out.println("\t" + project.getId() + "\t|\t" + project.getName());
            }
        }
    }
    public void selectTaskById(List<Task> tasks, int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" +task.getProjectID());
            }
        }
    }
    public void selectAllProjects(List<Project> projects) {
        for (Project project : projects) {
            System.out.println("\t" + project.getId() + "\t|\t" + project.getName());
        }
    }
    public void selectAllTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("\t" + task.getId() + "\t|\t" + task.getName() + "\t|\t" +task.getProjectID());
        }
    }


}
