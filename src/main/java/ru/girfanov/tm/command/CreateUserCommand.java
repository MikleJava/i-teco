package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.User;


public class CreateUserCommand extends AbstractCommand<String> {

    private static final String name = "-cu";
    private static final String description = "create user";

    public CreateUserCommand(Bootstrap bootstrap) {
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
        System.out.print("input user login : ");
        String login = scanner.next();
        System.out.print("input user password : ");
        String password = scanner.next();
        System.out.print("input user role : ");
        String role = scanner.next();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        bootstrap.userService.persist(user);
    }
}
