package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;

public class ExitCommand extends AbstractCommand<String> {

    private static final String name = "--exit";
    private static final String description = "close application";

    public ExitCommand(Bootstrap bootstrap) {
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

    }
}
