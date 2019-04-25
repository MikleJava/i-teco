package ru.girfanov.tm.command;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.endpoint.SessionDto;

@Getter
@NoArgsConstructor
public abstract class AbstractSystemCommand<T> {

    @NotNull private final String name = "asc";

    @NotNull private final String description = "abstract system command";

    @Setter protected ServiceLocator serviceLocator;

    private final boolean isSecure = false;

    public boolean isSecure() {
        return isSecure;
    }

    public abstract void execute(@NotNull final SessionDto sessionDto);
}
