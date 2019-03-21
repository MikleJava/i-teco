package ru.girfanov.tm;

import ru.girfanov.tm.create.Create;
import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;
import ru.girfanov.tm.delete.Delete;
import ru.girfanov.tm.info.Information;
import ru.girfanov.tm.select.Select;
import ru.girfanov.tm.update.Update;

import java.util.*;

public class App {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Information info = new Information();
        Scanner scanner = new Scanner(System.in);
        Select select = new Select();
        Create create = new Create();
        Update update = new Update();
        Delete delete = new Delete();
        String command = null;

        while (!"--exit".equals(command)) {
            command = scanner.nextLine();
            if(command.equals("--help")) {
                info.getInfo();
            }
            if(command.equals("-cp")) {
                create.createProject(projects);
            }
            if(command.equals("-ct")) {
                create.createTask(tasks);
            }
            if(command.equals("-up")) {
                update.updateProject(projects);
            }
            if(command.equals("-ut")) {
                update.updateTask(tasks);
            }
            if(command.equals("-dp")) {
                delete.deleteProject(projects);
            }
            if(command.equals("-dt")) {
                delete.deleteTask(tasks);
            }
            if(command.equals("-spbi")) {
                select.selectProjectById(projects);
            }
            if(command.equals("-stbi")) {
                select.selectTaskById(tasks);
            }
            if(command.equals("-sap")) {
                select.selectAllProjects(projects);
            }
            if(command.equals("-sat")) {
                select.selectAllTasks(tasks);
            }
        }
    }
}
