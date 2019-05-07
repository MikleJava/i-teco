package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.UserDto;
import ru.girfanov.tm.endpoint.UserEndPoint;

@Component
public final class UserDeleteCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-du";

    @Getter @NotNull private final String description = "delete user";

    @Autowired
    private UserEndPoint userEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final UserDto user = userEndPoint.findOneUser(sessionDto, sessionDto.getUserId());
        userEndPoint.removeUser(sessionDto, user);
    }
}
