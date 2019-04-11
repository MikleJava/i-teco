package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.User;
import ru.girfanov.tm.endpoint.UserEndPoint;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserPasswordUpdateCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-uup";

    @NotNull private final String description = "update user password";

    @Override
    public void execute(@NotNull final Session session) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        final User user = userEndPoint.findOneUserByLoginAndPassword(login, password);
        if(user != null) {
            System.out.print("input new password : ");
            final String newPassword = scanner.next();
            userEndPoint.mergeUserPassword(session, newPassword);
        }
    }
}