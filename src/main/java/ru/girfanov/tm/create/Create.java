//package ru.girfanov.tm.create;
//
//import ru.girfanov.tm.entity.Project;
//import ru.girfanov.tm.entity.Task;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Create {
//
//    private Scanner scanner = new Scanner(System.in);
//
//    private DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
//
//    public void createProject(List<Project> projects) {
//        UUID uuid = UUID.randomUUID();
//        try {
//            System.out.print("input project name : ");
//            String name = scanner.next();
//            System.out.print("input project description : ");
//            String description = scanner.next();
//            System.out.print("input date start : ");
//            Date dateStart = dateFormat.parse(scanner.next());
//            System.out.print("input date end : ");
//            Date dateEnd = dateFormat.parse(scanner.next());
//            projects.add(new Project(uuid.toString(), name, description, dateStart, dateEnd));
//        } catch (InputMismatchException e) {
//            System.out.println("Incorrect data");
//        } catch (ParseException e) {
//            System.out.println("Incorrect date");
//        }
//    }
//
//    public void createTask(List<Task> tasks) {
//        try {
//            UUID uuid = UUID.randomUUID();
//            System.out.print("input task name : ");
//            String name = scanner.next();
//            System.out.print("input task description : ");
//            String description = scanner.next();
//            System.out.print("input project id : ");
//            String projectID = scanner.next();
//            System.out.print("input date start : ");
//            Date dateStart = dateFormat.parse(scanner.next());
//            System.out.print("input date end : ");
//            Date dateEnd = dateFormat.parse(scanner.next());
//            tasks.add(new Task(uuid.toString(), name, description, projectID, dateStart, dateEnd));
//        } catch (InputMismatchException e) {
//            System.out.println("Incorrect data");
//        } catch (ParseException e) {
//            System.out.println("Incorrect date");
//        }
//    }
//}
