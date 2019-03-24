package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class CreateTaskCommand extends AbstractCommand<String> {

    private static final String name = "-ct";
    private static final String description = "create task";

    public CreateTaskCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        try {
            System.out.print("input task name : ");
            String name = scanner.next();
            System.out.print("input task description : ");
            String description = scanner.next();
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(bootstrap.projectService.findAll());
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id : ");
            int projectID = scanner.nextInt();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            bootstrap.taskService.persist(new Task(name, description, projects.get(projectID).getUuid(), dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}
