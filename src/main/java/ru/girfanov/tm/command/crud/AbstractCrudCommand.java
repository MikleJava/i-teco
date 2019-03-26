package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractCommand;

public abstract class AbstractCrudCommand extends AbstractCommand<String> {

    private final boolean isSecure = true;

    public AbstractCrudCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    public boolean isSecure() {
        return isSecure;
    }
}
