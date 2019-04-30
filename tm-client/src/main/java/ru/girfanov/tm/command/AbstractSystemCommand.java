package ru.girfanov.tm.command;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.exception.IncorrectRoleException;
import ru.girfanov.tm.exception.UserNotFoundException;

public abstract class AbstractSystemCommand<T> {

    @Getter @NotNull private final String name = "asc";

    @Getter @NotNull private final String description = "abstract system command";

    @Getter private final boolean isSecure = false;

    public abstract void execute(@NotNull final SessionDto sessionDto) throws UserNotFoundException, IncorrectRoleException;
}
