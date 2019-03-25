package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SelectProjectCommand extends AbstractCrudCommand {

    private static final String name = "-spbi";
    private static final String description = "select project by id";

    public SelectProjectCommand(Bootstrap bootstrap) {
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
            System.out.print("input project id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tname\t|\tdescription\t|\tdate_start\t|\tdate_end");
            System.out.println("_______________________________________________________________________________");
            Project project = bootstrap.projectService.findOne(projects.get(id).getUuid());
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
