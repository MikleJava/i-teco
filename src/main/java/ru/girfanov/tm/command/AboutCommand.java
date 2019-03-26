package ru.girfanov.tm.command;

import com.jcabi.manifests.Manifests;
import ru.girfanov.tm.api.ServiceLocator;

public class AboutCommand extends AbstractCommand<String> {

    private static final String name = "--about";
    private static final String description = "about app build";

    public AboutCommand(ServiceLocator serviceLocator) {
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
    public void execute(String... parameters) {
        System.out.println("Build num : " + Manifests.read("Build"));
    }
}
