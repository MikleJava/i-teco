package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public final class HelpCommand extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--help";

    @NotNull
    private final String description = "get information";

    @Override
    public void execute(@Nullable final String ... params) {
        System.out.println
                (
                "-cp\t create project \n" +
                "-ct\t create task \n" +
                "-up\t update project \n" +
                "-ut\t update task \n" +
                "-dp\t delete project \n" +
                "-dt\t delete task \n" +
                "-spbi\t select project by id \n" +
                "-stbi\t select task by id \n" +
                "-sap\t select all projects \n" +
                "-sat\t select all tasks \n" +
                "-satbpi\t select all tasks by project id"
                );
    }
}
