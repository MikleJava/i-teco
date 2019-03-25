package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.crud.AbstractCrudCommand;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class CreateProjectCommand extends AbstractCrudCommand {

    private static final String name = "-cp";
    private static final String description = "create project";

    public CreateProjectCommand(Bootstrap bootstrap) {
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
            System.out.print("input project name : ");
            String name = scanner.next();
            System.out.print("input project description : ");
            String description = scanner.next();
            System.out.println("all available users : ");
            List<User> users = new ArrayList<>(bootstrap.userService.findAll());
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getLogin());
            }
            System.out.print("input user id : ");
            int userId = scanner.nextInt();
            System.out.print("input date start : ");
            Date dateStart = dateFormat.parse(scanner.next());
            System.out.print("input date end : ");
            Date dateEnd = dateFormat.parse(scanner.next());
            bootstrap.projectService.persist(new Project(name, description, users.get(userId).getUuid(), dateStart, dateEnd));
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }
}
