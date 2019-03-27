package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.User;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class UsersSelectAllCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-sau";

    @NotNull
    private final String description = "select all users";

    @Override
    public void execute(@Nullable final String ... params) {
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        final Collection<User> users = serviceLocator.getUserService().findAll();
        for (User user : users) {
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getName() + "\t|\t" + user.getRole());
        }
    }
}
