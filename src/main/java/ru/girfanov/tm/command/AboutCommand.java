package ru.girfanov.tm.command;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;

public class AboutCommand extends AbstractCommand<String> {

    @NotNull
    private static final String name = "--about";
    @NotNull
    private static final String description = "about app build";

    public AboutCommand(@NotNull ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(@Nullable final String... parameters) {
        System.out.println("Build num : " + Manifests.read("Implementation-Build"));
    }
}
