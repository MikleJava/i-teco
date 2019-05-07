package ru.girfanov.tm.command.crud;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.*;

import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Component
public final class UserSelectCommand extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "-subi";

    @Getter @NotNull private final String description = "select user by id";

    @Autowired
    private UserEndPoint userEndPoint;

    @Override
    public void execute(@NotNull final SessionDto sessionDto) {
        try {
            System.out.println("all available users : ");
            final List<UserDto> users = new ArrayList<>(userEndPoint.findAllUsers(sessionDto));
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getId() + " | " + users.get(i).getLogin());
            }
            System.out.print("input user id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tlogin\t|\trole");
            System.out.println("_______________________________________________________________________________________________");
            final UserDto user = userEndPoint.findOneUser(sessionDto, users.get(id).getId());
            System.out.println("\t" + user.getId() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
