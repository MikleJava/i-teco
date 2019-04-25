package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserUpdateCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-uu";

    @NotNull private final String description = "update user";

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        final UserDto user = userEndPoint.findOneUserByLogin(login);
        if(user != null) {
            System.out.print("input new password : ");
            final String newPassword = scanner.next();
            user.setPassword(newPassword);
            userEndPoint.mergeUser(sessionDto, user);
        }
    }
}
