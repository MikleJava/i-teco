package ru.girfanov.tm.command.crud;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.User;

public class UpdateUserPasswordCommand extends AbstractCrudCommand {

    private static final String name = "-uup";
    private static final String description = "update user password";

    public UpdateUserPasswordCommand(Bootstrap bootstrap) {
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
    public void execute(String ... params) {
        System.out.print("input user login : ");
        String login = scanner.next();
        System.out.print("input user password : ");
        String password = scanner.next();
        User user = bootstrap.userService.findOneByLoginAndPassword(login, password);
        if(user != null) {
            System.out.print("input new password : ");
            String newPassword = scanner.next();
            bootstrap.userService.mergeUserPassword(user.getUuid(), newPassword);
        }
    }
}
