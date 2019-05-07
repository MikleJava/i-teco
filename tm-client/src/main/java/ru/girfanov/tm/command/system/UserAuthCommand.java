package ru.girfanov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.UserEndPoint;
import ru.girfanov.tm.exception.UserNotFoundException;

import static ru.girfanov.tm.util.Terminal.*;

@Component
public final class UserAuthCommand extends AbstractSystemCommand<String> {

    @Getter @NotNull private final String name = "-au";

    @Getter @NotNull private final String description = "auth user";

    @Autowired
    protected ServiceLocator serviceLocator;
    @Autowired
    private UserEndPoint userEndPoint;

    @Override
    public void execute(@Nullable final SessionDto session) throws UserNotFoundException {
        System.out.print("input login : ");
        final String login = scanner.next();
        if(userEndPoint.findOneUserByLogin(login) == null) throw new UserNotFoundException("User not found");
    }
}
