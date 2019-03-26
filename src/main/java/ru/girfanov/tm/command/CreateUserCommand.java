package ru.girfanov.tm.command;

import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;


public final class CreateUserCommand extends AbstractCommand<String> {

    private static final String name = "-cu";
    private static final String description = "create user";

    public CreateUserCommand(final ServiceLocator serviceLocator) {
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

    public void execute(String ... params) {
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
        serviceLocator.getUserService().persist(user);
    }
}
