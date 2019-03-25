package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;

public abstract class AbstractCrudCommand extends AbstractCommand<String> {

    public AbstractCrudCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    private boolean isSecure = true;

    public boolean isSecure() {
        return isSecure;
    }
}
