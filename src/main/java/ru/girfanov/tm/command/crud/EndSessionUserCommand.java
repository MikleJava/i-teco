package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;

public final class EndSessionUserCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-esu";
    @NotNull
    private static final String description = "end session user";

    public EndSessionUserCommand(@NotNull final ServiceLocator serviceLocator) {
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
        serviceLocator.getUserService().remove(params[0]);
    }
}
