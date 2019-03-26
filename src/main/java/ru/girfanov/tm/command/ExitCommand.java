package ru.girfanov.tm.command;

import ru.girfanov.tm.api.ServiceLocator;

public final class ExitCommand extends AbstractCommand<String> {

    private static final String name = "--exit";
    private static final String description = "close application";

    public ExitCommand(final ServiceLocator serviceLocator) {
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

    public void execute(String ... params) {}
}
