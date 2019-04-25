package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.UserDto;
import ru.girfanov.tm.endpoint.UserEndPoint;

@Getter
@NoArgsConstructor
public final class UserDeleteCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-du";

    @NotNull private final String description = "delete user";

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        final UserDto user = userEndPoint.findOneUser(sessionDto, sessionDto.getUserId());
        userEndPoint.removeUser(sessionDto, user);
    }
}
