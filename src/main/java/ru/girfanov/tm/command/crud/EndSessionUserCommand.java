package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;

public class EndSessionUserCommand extends AbstractCrudCommand {

    private static final String name = "-esu";
    private static final String description = "end session user";

    public EndSessionUserCommand(Bootstrap bootstrap) {
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
    public void execute(String ... params) {
        bootstrap.userService.remove(params[0]);
    }
}
