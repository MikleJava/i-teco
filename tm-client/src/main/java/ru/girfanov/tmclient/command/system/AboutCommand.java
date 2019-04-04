package ru.girfanov.tmclient.command.system;

import com.jcabi.manifests.Manifests;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmclient.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class AboutCommand extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--about";

    @NotNull
    private  final String description = "about app build";

    @Override
    public void execute(@Nullable final String... parameters) {
        System.out.println("Build num : " + Manifests.read("Implementation-Build"));
    }
}
