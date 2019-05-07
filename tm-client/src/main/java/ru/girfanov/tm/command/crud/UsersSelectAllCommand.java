package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;
import java.util.List;

@Component
public final class UsersSelectAllCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-sau";

    @Getter @NotNull private final String description = "select all users";

    @Autowired
    private UserEndPoint userEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        System.out.println("\tid\t|\tlogin\t|\trole");
        System.out.println("___________________________________________________________________________________________________");
        final List<UserDto> users = userEndPoint.findAllUsers(sessionDto);
        for (UserDto user : users) {
            System.out.println("\t" + user.getId() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        }
    }
}
