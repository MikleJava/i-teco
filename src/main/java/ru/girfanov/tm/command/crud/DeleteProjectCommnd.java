package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DeleteProjectCommnd extends AbstractCrudCommand {

    private static final String name = "-dp";
    private static final String description = "delete project";

    public DeleteProjectCommnd(Bootstrap bootstrap) {
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
    public void execute(String ... params) {
        try {
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(bootstrap.projectService.findAllProjectsByUserId(params[0]));
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to delete : ");
            int id = scanner.nextInt();
            bootstrap.projectService.remove(projects.get(id).getUuid());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
