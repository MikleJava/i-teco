package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;

import java.util.Collection;

public final class SelectAllUsersCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-sau";
    @NotNull
    private static final String description = "select all users";

    public SelectAllUsersCommand(@NotNull final ServiceLocator serviceLocator) {
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
    public void execute(@Nullable final String ... params) {
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        Collection<User> users = serviceLocator.getUserService().findAll();
        for (User user : users) {
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
