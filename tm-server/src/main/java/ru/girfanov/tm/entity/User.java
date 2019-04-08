package ru.girfanov.tm.entity;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -3316296014185311021L;

    @NonNull
    @Setter
    private String login;

    @NonNull
    private String password;

    @NonNull
    @Setter //temporary
    private String role;

    public void setPassword(@NotNull final String password) {
        this.password = DigestUtils.md5Hex(password);
    }

//    public void setRole(@NotNull final String role) {
//        if("Администратор".equals(role)) { this.role = Role.ADMINISTRATOR; }
//        if("Пользователь".equals(role)) { this.role = Role.USER; }
//    }
}
