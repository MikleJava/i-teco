package ru.girfanov.tm.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.endpoint.SessionDto;

@NoArgsConstructor
public abstract class AbstractSecureCommand extends AbstractSystemCommand<SessionDto> {

    @NotNull private final String name = "asec";

    @NotNull private final String description = "abstract secure command";

    private final boolean isSecure = true;

    public boolean isSecure() {
        return isSecure;
    }
}
