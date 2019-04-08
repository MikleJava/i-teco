package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tmserver.endpoint.User;
import ru.girfanov.tmserver.endpoint.UserEndPoint;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class UsersSelectAllCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-sau";

    @NotNull
    private final String description = "select all users";

    @Override
    public void execute(@NotNull final String ... params) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        final Collection<User> users = userEndPoint.findAllUsers(params[0]);
        for (User user : users) {
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
