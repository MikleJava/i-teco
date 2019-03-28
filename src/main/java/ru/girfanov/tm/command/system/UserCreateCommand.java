package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tm.entity.User;
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
        final String name = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        System.out.print("input user role : ");
        final String role = scanner.next();
        final User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        serviceLocator.getUserService().persist(user, user.getUuid());
    }
}
