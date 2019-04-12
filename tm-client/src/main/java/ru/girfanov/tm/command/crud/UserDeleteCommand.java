package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.User;
import ru.girfanov.tm.endpoint.UserEndPoint;

@Getter
@NoArgsConstructor
public final class UserDeleteCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-du";

    @NotNull private final String description = "delete user";

    @Override
    public void execute(@NotNull final Session session) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        final User user = userEndPoint.findOneUser(session, session.getUserId());
        userEndPoint.removeUser(session, user);
    }
}
