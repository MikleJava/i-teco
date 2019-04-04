package ru.girfanov.tmclient.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmclient.command.AbstractSystemCommand;
import ru.girfanov.tmserver.entity.User;
import static ru.girfanov.tmserver.util.Terminal.*;

@Getter
@NoArgsConstructor
public final class UserAuthCommand extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "-au";

    @NotNull
    private final String description = "auth user";

    @Override
    public void execute(@Nullable final String ... params) {
        System.out.print("input user login : ");
        final String login = scanner.next();
        System.out.print("input user password : ");
        final String password = scanner.next();
        final User user = serviceLocator.getUserService().findOneByLoginAndPassword(login, DigestUtils.md5Hex(password));
        if(user == null) {
            serviceLocator.setUser(null);
            System.out.println("This user does not exist");
        }
        else {
            serviceLocator.setUser(user);}
    }
}
