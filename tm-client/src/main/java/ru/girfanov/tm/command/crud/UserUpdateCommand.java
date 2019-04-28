package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static ru.girfanov.tm.util.Terminal.*;

@ApplicationScoped
@NoArgsConstructor
public final class UserUpdateCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-uu";

    @Getter @NotNull private final String description = "update user";

    @Inject
    private UserEndPoint userEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
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
