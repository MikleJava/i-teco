package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;

import javax.enterprise.context.ApplicationScoped;

@Getter
@ApplicationScoped
@NoArgsConstructor
public final class ExitCommand extends AbstractSystemCommand<String> {

    @NotNull private final String name = "--exit";

    @NotNull private final String description = "close application";

    @Override
    public void execute(@Nullable final SessionDto session) {
        System.exit(0);
    }
}
