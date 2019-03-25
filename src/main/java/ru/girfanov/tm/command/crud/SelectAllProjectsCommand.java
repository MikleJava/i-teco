package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public class SelectAllProjectsCommand extends AbstractCrudCommand {

    private static final String name = "-sap";
    private static final String description = "select all projects";

    public SelectAllProjectsCommand(Bootstrap bootstrap) {
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
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("____________________________________________________________________________________________________________________________________");
        Collection<Project> projects = bootstrap.projectService.findAllProjectsByUserId(params[0]);
        for (Project project : projects) {
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getUserId() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }
}
