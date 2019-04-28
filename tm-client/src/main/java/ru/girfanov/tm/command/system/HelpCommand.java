package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.ApplicationClient;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.endpoint.SessionDto;

import javax.enterprise.context.ApplicationScoped;

@Getter
@ApplicationScoped
@NoArgsConstructor
public final class HelpCommand extends AbstractSystemCommand<String> {

    @NotNull private final String name = "--help";

    @NotNull private final String description = "get information";

    @Override
    public void execute(@Nullable final SessionDto session) {
        for (Class clazz : ApplicationClient.commandClasses) {
            try {
                AbstractSystemCommand<String> command = (AbstractSystemCommand<String>) clazz.newInstance();
                System.out.println(command.getName() + "\t\t" + command.getDescription());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
