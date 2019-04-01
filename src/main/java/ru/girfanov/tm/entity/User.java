package ru.girfanov.tm.entity;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.entity.enumeration.Role;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    @NonNull
    @Setter
    private String login;

    @NonNull
    private String password;

    @NonNull
    private Role role;

    public void setPassword(@NotNull final String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    public void setRole(@NotNull final String role) {
        if("Администратор".equals(role)) { this.role = Role.ADMINISTRATOR; }
        if("Пользователь".equals(role)) { this.role = Role.USER; }
    }
}
