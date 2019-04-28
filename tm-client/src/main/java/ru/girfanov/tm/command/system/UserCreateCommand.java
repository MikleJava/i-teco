package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.exception.IncorrectRoleException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

import static ru.girfanov.tm.util.Terminal.*;

@ApplicationScoped
@NoArgsConstructor
public final class UserCreateCommand extends AbstractSystemCommand<String> {

    @Getter @NotNull private final String name = "-cu";

    @Getter @NotNull private final String description = "create user";

    @Inject
    protected ServiceLocator serviceLocator;
    @Inject
    private UserEndPoint userEndPoint;
    @Inject
    private SessionEndPoint sessionEndPoint;

    @Override
    public void execute(@Nullable final SessionDto session) throws IncorrectRoleException {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final UserDto user = new UserDto();
        user.setId(UUID.randomUUID().toString());
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        if(user.getRole() == null) throw new IncorrectRoleException("Incorrect role");
        userEndPoint.persistUser(user);
        final SessionDto userSession = sessionEndPoint.createSession(login);
        serviceLocator.setSessionDto(userSession);
    }
}
