package ru.girfanov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.SessionEndPoint;

import javax.inject.Inject;

@Getter
public final class ServerInfoCommand extends AbstractSystemCommand<String>{

    @NotNull private final String name = "--sinf";

    @NotNull private final String description = "get server information";

    @Inject private SessionEndPoint sessionEndPoint;

    @Override
    public void execute(@Nullable SessionDto sessionDto) {
        System.out.println(sessionEndPoint.getServerInfo());
    }
}
