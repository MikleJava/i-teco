package ru.girfanov.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;


public final class CreateUserCommand extends AbstractCommand<String> {

    @NotNull
    private static final String name = "-cu";
    @NotNull
    private static final String description = "create user";

    public CreateUserCommand(@NotNull final ServiceLocator serviceLocator) {
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

    public void execute(@Nullable final String ... params) {
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
