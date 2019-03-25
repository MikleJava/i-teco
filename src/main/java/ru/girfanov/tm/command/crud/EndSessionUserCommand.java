package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractCommand;
import ru.girfanov.tm.entity.User;

import java.util.*;

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
    public void execute() {
        System.out.println("all available user sessions : ");
        List<User> users = new ArrayList<>(bootstrap.userService.findAll());
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getLogin());
        }
        System.out.print("input user id which you want to delete : ");
        int id = scanner.nextInt();
        bootstrap.userService.remove(users.get(id).getUuid());
    }
}
