package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.User;
import ru.girfanov.tm.endpoint.UserEndPoint;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class UserSelectCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-subi";

    @NotNull private final String description = "select user by id";

    @Override
    public void execute(@NotNull final Session session) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        try {
            System.out.println("all available users : ");
            final List<User> users = new ArrayList<>(userEndPoint.findAllUsers(session));
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getLogin());
            }
            System.out.print("input user id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tlogin\t|\trole");
            System.out.println("_______________________________________________________________________________________________");
            final User user = userEndPoint.findOneUser(session, users.get(id).getUuid());
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
