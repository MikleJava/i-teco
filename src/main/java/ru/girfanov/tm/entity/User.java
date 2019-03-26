package ru.girfanov.tm.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.enumeration.Role;

import java.util.Objects;


public final class User extends AbstractEntity {

    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Role role;

    public User() {
    }

    public User(@NotNull final String login, @NotNull final String password, @NotNull final Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public User setLogin(@NotNull final String login) {
        this.login = login;
        return this;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public User setPassword(@NotNull final String password) {
        this.password = DigestUtils.md5Hex(password);
        return this;
    }

    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(@NotNull final String role) {
        if("Администратор".equals(role)) { this.role = Role.ADMINISTRATOR; }
        if("Пользователь".equals(role)) { this.role = Role.USER; }
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUuid(), user.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
