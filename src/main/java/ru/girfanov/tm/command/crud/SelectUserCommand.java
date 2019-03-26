package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public final class SelectUserCommand extends AbstractCrudCommand {

    @NotNull
    private static final String name = "-subi";
    @NotNull
    private static final String description = "select user by id";

    public SelectUserCommand(@NotNull final ServiceLocator serviceLocator) {
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
        try {
            System.out.println("all available users : ");
            List<User> users = new ArrayList<>(serviceLocator.getUserService().findAll());
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getLogin());
            }
            System.out.print("input user id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tlogin\t|\trole");
            System.out.println("_______________________________________________________________________________________________");
            User user = serviceLocator.getUserService().findOne(users.get(id).getUuid());
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
