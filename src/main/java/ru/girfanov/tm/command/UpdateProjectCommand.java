package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class UpdateProjectCommand extends AbstractCommand<String> {

    private static final String name = "-up";
    private static final String description = "update project";

    public UpdateProjectCommand(Bootstrap bootstrap) {
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
            System.out.println("all available projects : ");
            List<Project> projects = new ArrayList<>(bootstrap.projectService.findAll());
            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + ") " + projects.get(i).getUuid() + " | " + projects.get(i).getName());
            }
            System.out.print("input project id which you want to update : ");
            int id = scanner.nextInt();
            System.out.print("input new project name : ");
            String name = scanner.next();
            bootstrap.projectService.merge(projects.get(id).getUuid(), name);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
