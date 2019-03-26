package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;

public final class UpdateUserPasswordCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-uup";
    @NotNull
    private static final String description = "update user password";

    public UpdateUserPasswordCommand(@NotNull final ServiceLocator serviceLocator) {
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
    public void execute(@Nullable final String ... params) {
        System.out.print("input user login : ");
        String login = scanner.next();
        System.out.print("input user password : ");
        String password = scanner.next();
        User user = serviceLocator.getUserService().findOneByLoginAndPassword(login, password);
        if(user != null) {
            System.out.print("input new password : ");
            String newPassword = scanner.next();
            serviceLocator.getUserService().mergeUserPassword(user.getUuid(), newPassword);
        }
    }
}
