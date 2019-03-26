package ru.girfanov.tm.command;

import org.apache.commons.codec.digest.DigestUtils;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.entity.User;

public final class AuthUserCommand extends AbstractCommand<String> {

    private static final String name = "-au";
    private static final String description = "auth user";

    public AuthUserCommand(final ServiceLocator serviceLocator) {
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
    public void execute(String ... params) {
        System.out.print("input user login : ");
        String login = scanner.next();
        System.out.print("input user password : ");
        String password = scanner.next();
        User user = serviceLocator.getUserService().findOneByLoginAndPassword(login, DigestUtils.md5Hex(password));
        if(user == null) {
            serviceLocator.setUser(null);
            System.out.println("This user does not exist");
        }
        else {
            serviceLocator.setUser(user);}
    }
}
