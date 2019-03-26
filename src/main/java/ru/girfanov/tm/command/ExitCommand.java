package ru.girfanov.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;

public final class ExitCommand extends AbstractCommand<String> {

    @NotNull
    private static final String name = "--exit";
    @NotNull
    private static final String description = "close application";

    public ExitCommand(@NotNull final ServiceLocator serviceLocator) {
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

    public void execute(@Nullable final String ... params) {}
}
