package ru.girfanov.tm.command;

import ru.girfanov.tm.api.ServiceLocator;

public final class HelpCommand extends AbstractCommand<String> {

    private static final String name = "--help";
    private static final String description = "get information";

    public HelpCommand(final ServiceLocator serviceLocator) {
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

    public void execute(String ... params) {
        System.out.println("-cp\t create project \n" +
                "-ct\t create task \n" +
                "-up\t update project \n" +
                "-ut\t update task \n" +
                "-dp\t delete project \n" +
                "-dt\t delete task \n" +
                "-spbi\t select project by id \n" +
                "-stbi\t select task by id \n" +
                "-sap\t select all projects \n" +
                "-sat\t select all tasks \n" +
                "-satbpi\t select all tasks by project id");
    }
}
