package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

@Component
public final class UserUpdateCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-uu";

    @Getter @NotNull private final String description = "update user";

    @Autowired
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
