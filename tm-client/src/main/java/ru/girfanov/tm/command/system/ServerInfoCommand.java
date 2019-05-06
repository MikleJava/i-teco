package ru.girfanov.tm.command.system;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;
import ru.girfanov.tm.endpoint.SessionEndPoint;

@Component
public final class ServerInfoCommand extends AbstractSystemCommand<String>{

    @Getter @NotNull private final String name = "--sinf";

    @Getter @NotNull private final String description = "get server information";

    @Autowired
    private SessionEndPoint sessionEndPoint;

    @Override
    public void execute(@Nullable SessionDto sessionDto) {
        System.out.println(sessionEndPoint.getServerInfo());
    }
}
