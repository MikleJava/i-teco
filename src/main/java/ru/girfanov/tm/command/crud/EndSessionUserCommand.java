package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.api.ServiceLocator;

public final class EndSessionUserCommand extends AbstractCrudCommand {

    private static final String name = "-esu";
    private static final String description = "end session user";

    public EndSessionUserCommand(final ServiceLocator serviceLocator) {
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
    public void execute(String ... params) {
        serviceLocator.getUserService().remove(params[0]);
    }
}
