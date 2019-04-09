package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.SessionEndPoint;
import ru.girfanov.tm.exception.IncorrectRoleException;
import ru.girfanov.tm.endpoint.User;
import ru.girfanov.tm.endpoint.UserEndPoint;

import java.util.UUID;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserCreateCommand extends AbstractSystemCommand<String> {

    @NotNull private final String name = "-cu";

    @NotNull private final String description = "create user";

    @Override
    public void execute(@Nullable final Session session) throws IncorrectRoleException {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        final SessionEndPoint sessionEndPoint = serviceLocator.getSessionEndPoint();
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if(user.getRole() == null) throw new IncorrectRoleException("Incorrect role");
        userEndPoint.persistUser(user);
        final Session userSession = sessionEndPoint.createSession(login, DigestUtils.md5Hex(password));
        serviceLocator.setSession(userSession);
    }
}
