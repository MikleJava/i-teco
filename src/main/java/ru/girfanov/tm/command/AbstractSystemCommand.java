package ru.girfanov.tm.command;

import lombok.*;
import ru.girfanov.tm.api.ServiceLocator;

@Getter
@NoArgsConstructor
public abstract class AbstractSystemCommand<T> {

    private final String name = "asc";

    @Setter
    protected ServiceLocator serviceLocator;

    private final boolean isSecure = false;

    public boolean isSecure() {
        return isSecure;
    }

    public abstract void execute(final T ... parameters);
}
