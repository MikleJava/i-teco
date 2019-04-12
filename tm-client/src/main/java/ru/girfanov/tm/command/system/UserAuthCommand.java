package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.SessionEndPoint;
import ru.girfanov.tm.endpoint.UserEndPoint;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.util.PasswordHashUtil;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserAuthCommand extends AbstractSystemCommand<String> {

    @NotNull private final String name = "-au";

    @NotNull private final String description = "auth user";

    @Override
    public void execute(@Nullable final Session session) throws UserNotFoundException {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        final SessionEndPoint sessionEndPoint = serviceLocator.getSessionEndPoint();
        System.out.print("input login : ");
        final String login = scanner.next();
        System.out.print("input password : ");
        final String password = scanner.next();
        if(userEndPoint.findOneUserByLoginAndPassword(login, PasswordHashUtil.md5(password)) == null) throw new UserNotFoundException("User not found");
        final Session userSession = sessionEndPoint.createSession(login, PasswordHashUtil.md5(password));
        serviceLocator.setSession(userSession);
    }
}
