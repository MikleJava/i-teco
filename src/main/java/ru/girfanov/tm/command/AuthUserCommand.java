package ru.girfanov.tm.command;

import org.apache.commons.codec.digest.DigestUtils;
import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.entity.User;

public class AuthUserCommand extends AbstractCommand<String> {

    private static final String name = "-au";
    private static final String description = "auth user";

    public AuthUserCommand(Bootstrap bootstrap) {
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
        User user = bootstrap.userService.findOneByLoginAndPassword(login, DigestUtils.md5Hex(password));
        if(user == null) { System.out.println("You must be logged in"); }
        else {bootstrap.setUser(user);}
    }
}
