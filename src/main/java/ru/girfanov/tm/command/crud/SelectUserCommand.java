package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SelectUserCommand extends AbstractCrudCommand {

    private static final String name = "-subi";
    private static final String description = "select user by id";

    public SelectUserCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
    public void execute() {
        try {
            System.out.println("all available users : ");
            List<User> users = new ArrayList<>(bootstrap.userService.findAll());
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + ") " + users.get(i).getUuid() + " | " + users.get(i).getLogin());
            }
            System.out.print("input user id : ");
            int id = scanner.nextInt();
            System.out.println("\tid\t|\tlogin\t|\trole");
            System.out.println("_______________________________________________________________________________________________");
            User user = bootstrap.userService.findOne(users.get(id).getUuid());
            System.out.println("\t" + user.getUuid() + "\t|\t" + user.getLogin() + "\t|\t" + user.getRole());
        } catch (InputMismatchException e) {
            System.out.println("Incorrect data");
        }
    }
}
