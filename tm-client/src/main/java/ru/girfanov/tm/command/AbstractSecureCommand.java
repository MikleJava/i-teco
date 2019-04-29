package ru.girfanov.tm.command;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.endpoint.SessionDto;

public abstract class AbstractSecureCommand extends AbstractSystemCommand<SessionDto> {

    @Getter @NotNull private final String name = "asec";

    @Getter @NotNull private final String description = "abstract secure command";

    @Getter private final boolean isSecure = true;

}
