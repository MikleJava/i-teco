package ru.girfanov.tm.command.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractCrudCommand;
import ru.girfanov.tm.entity.User;
import static ru.girfanov.tm.util.Terminal.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Getter
@NoArgsConstructor
public final class UserSelectCommand extends AbstractCrudCommand {

    @NotNull
    private final String name = "-subi";

    @NotNull
    private final String description = "select user by id";

    @Override
    public void execute(@Nullable final String ... params) {
        try {
            System.out.println("all available users : ");
            final List<User> users = new ArrayList<>(serviceLocator.getUserService().findAll());
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getName());
            }
            System.out.print("input user id : ");
            final int id = scanner.nextInt();
            System.out.println("\tid\t|\tlogin\t|\trole");
            System.out.println("_______________________________________________________________________________________________");
            final User user = serviceLocator.getUserService().findOne(users.get(id).getUuid());
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getName() + "\t|\t" + user.getRole());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
