package ru.girfanov.tmclient.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmclient.command.AbstractSystemCommand;
import ru.girfanov.tmclient.exception.IncorrectRoleException;
import ru.girfanov.tmserver.endpoint.User;
import ru.girfanov.tmserver.endpoint.UserEndPoint;

import java.util.UUID;

import static ru.girfanov.tmclient.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserCreateCommand extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "-cu";

    @NotNull
    private final String description = "create user";

    @Override
    public void execute(@Nullable final String ... params) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        final User user = new User();
        user.setUuid(uuid);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if(user.getRole() == null) throw new IncorrectRoleException("Incorrect role");
        userEndPoint.persistUser(user.getUuid(), user);
    }
}
