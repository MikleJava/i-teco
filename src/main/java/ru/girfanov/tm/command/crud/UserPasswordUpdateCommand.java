package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.User;
import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserPasswordUpdateCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-uup";
    @NotNull
    private final String description = "update user password";

    @Override
    public void execute(@Nullable final String ... params) {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        final User user = serviceLocator.getUserService().findOneByNameAndPassword(login, password);
        if(user != null) {
            System.out.print("input new password : ");
            final String newPassword = scanner.next();
            serviceLocator.getUserService().mergeUserPassword(user.getUuid(), newPassword);
        }
    }
}
