package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.Project;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class ProjectsSelectAllCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-sap";

    @NotNull
    private final String description = "select all projects";

    @Override
    public void execute(@NotNull final String ... params) {
        System.out.println("\tid\t|\tname\t|\tdescription\t|\tuser_id\t|\tdate_start\t|\tdate_end");
        System.out.println("____________________________________________________________________________________________________________________________________");
        final Collection<Project> projects = serviceLocator.getProjectService().findAll(params[0]);
        for (Project project : projects) {
            System.out.println("\t" + project.getUuid() + "\t|\t" + project.getName() + "\t|\t" + project.getDescription() + "\t|\t" + project.getUserId() + "\t|\t" + project.getDateStart() + "\t|\t" + project.getDateEnd());
        }
    }
}
