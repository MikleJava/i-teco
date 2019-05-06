package ru.girfanov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.exception.IncorrectRoleException;

import java.util.UUID;

import static ru.girfanov.tm.util.Terminal.*;

@Component
public final class UserCreateCommand extends AbstractSystemCommand<String> {

    @Getter @NotNull private final String name = "-cu";

    @Getter @NotNull private final String description = "create user";

    @Autowired
    protected ServiceLocator serviceLocator;
    @Autowired
    private UserEndPoint userEndPoint;
    @Autowired
    private SessionEndPoint sessionEndPoint;

    @Override
    public void execute(@Nullable final SessionDto session) throws IncorrectRoleException {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final  String id = UUID.randomUUID().toString();
        if(Role.valueOf(role) == null) throw new IncorrectRoleException("Incorrect role");
        userEndPoint.persistUser(id, login, password, Role.valueOf(role));
        final SessionDto userSession = sessionEndPoint.createSession(login);
        serviceLocator.setSessionDto(userSession);
    }
}
