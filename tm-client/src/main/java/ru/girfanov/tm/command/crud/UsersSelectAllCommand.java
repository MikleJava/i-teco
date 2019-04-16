package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.User;
import ru.girfanov.tm.endpoint.UserEndPoint;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class UsersSelectAllCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-sau";

    @NotNull private final String description = "select all users";

    @Override
    public void execute(@NotNull final Session session) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        final Collection<User> users = userEndPoint.findAllUsers(session);
        for (User user : users) {
            System.out.println("\t" + user.getId() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
