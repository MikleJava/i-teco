package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.User;

import java.util.Collection;

public class SelectAllUsersCommand extends AbstractCrudCommand {

    private static final String name = "-sau";
    private static final String description = "select all users";

    public SelectAllUsersCommand(Bootstrap bootstrap) {
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
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        Collection<User> users = bootstrap.userService.findAll();
        for (User user : users) {
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
