package ru.girfanov.tmclient.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmclient.command.AbstractCrudCommand;
import ru.girfanov.tmserver.entity.User;
import static ru.girfanov.tmserver.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserPasswordUpdateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-uup";
    @NotNull
    private final String description = "update user password";

    @Override
    public void execute(@NotNull final String ... params) {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        final User user = serviceLocator.getUserService().findOneByLoginAndPassword(login, password);
        if(user != null) {
            System.out.print("input new password : ");
            final String newPassword = scanner.next();
            serviceLocator.getUserService().mergePassword(params[0], newPassword);
        }
    }
}
