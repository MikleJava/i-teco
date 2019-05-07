package ru.girfanov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.ApplicationClient;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;

import java.util.Map;

@Component
public final class HelpCommand extends AbstractSystemCommand<String> {

    @Getter @NotNull private final String name = "--help";

    @Getter @NotNull private final String description = "get information";

    @Autowired
    private Bootstrap bootstrap;

    @Override
    public void execute(@Nullable final SessionDto session) {
        final Map<String, AbstractSystemCommand<String>> map = bootstrap.getMapCommands();
        for (AbstractSystemCommand command : map.values()) {
            System.out.println(command.getName() + "\t\t" + command.getDescription());
        }
    }
}
