package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.UserEndPoint;

@Getter
@NoArgsConstructor
public final class UserEndSessionCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-esu";

    @NotNull
    private final String description = "end session user";

    @Override
    public void execute(@NotNull final String ... params) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        userEndPoint.removeUser(params[0], params[0]);
    }
}
