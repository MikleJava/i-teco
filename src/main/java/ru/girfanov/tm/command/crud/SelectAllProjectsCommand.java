package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

public final class SelectAllProjectsCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-sap";
    @NotNull
    private static final String description = "select all projects";

    public SelectAllProjectsCommand(@NotNull final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
    public void execute(@NotNull final String ... params) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("____________________________________________________________________________________________________________________________________");
        Collection<Project> projects = serviceLocator.getProjectService().findAllProjectsByUserId(params[0]);
        for (Project project : projects) {
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getUserId() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }
}
