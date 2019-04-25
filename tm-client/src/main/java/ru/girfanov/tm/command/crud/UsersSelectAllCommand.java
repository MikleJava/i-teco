package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import java.util.List;

@Getter
@NoArgsConstructor
public final class UsersSelectAllCommand extends AbstractSecureCommand {

    @NotNull private final String name = "-sau";

    @NotNull private final String description = "select all users";

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        final UserEndPoint userEndPoint = serviceLocator.getUserEndPoint();
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        final List<UserDto> users = userEndPoint.findAllUsers(sessionDto);
        for (UserDto user : users) {
            System.out.println("\t" + user.getId() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
