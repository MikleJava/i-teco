package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.IncorrectRoleException;

import static ru.girfanov.tm.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserCreateCommand extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "-cu";

    @NotNull
    private final String description = "create user";

    @Override
    public void execute(@Nullable final String ... params) {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if(user.getRole() == null) throw new IncorrectRoleException("Incorrect role");
        serviceLocator.getUserService().persist(user.getUuid(), user);
    }
}
