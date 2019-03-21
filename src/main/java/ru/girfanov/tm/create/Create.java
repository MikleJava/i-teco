package ru.girfanov.tm.create;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Create {

    private Scanner scanner = new Scanner(System.in);

    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    public void createProject(List<Project> projects) {
        try {
            System.out.print("input project id : ");
            int id = scanner.nextInt();
            System.out.print("input project name : ");
            String name = scanner.next();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            projects.add(new Project(id, name, dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }

    public void createTask(List<Task> tasks) {
        try {
            System.out.print("input task id : ");
            int id = scanner.nextInt();
            System.out.print("input task name : ");
            String name = scanner.next();
            System.out.print("input project id : ");
            int projectID = scanner.nextInt();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            tasks.add(new Task(id, name, projectID, dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}
