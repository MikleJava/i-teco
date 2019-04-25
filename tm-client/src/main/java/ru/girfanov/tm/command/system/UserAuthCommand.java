package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.UserEndPoint;
import ru.girfanov.tm.exception.UserNotFoundException;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserAuthCommand extends AbstractSystemCommand<String> {

    @NotNull private final String name = "-au";

    @NotNull private final String description = "auth user";

    @Override
    public void execute(@Nullable final SessionDto session) throws UserNotFoundException {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.print("input login : ");
        final String login = scanner.next();
        if(userEndPoint.findOneUserByLogin(login) == null) throw new UserNotFoundException("User not found");
    }
}
