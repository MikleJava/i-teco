package ru.girfanov.tm.entity;

import org.apache.commons.codec.digest.DigestUtils;
import ru.girfanov.tm.entity.enumeration.Role;

import java.util.Objects;


public class User extends AbstractEntity {

    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        if("Администратор".equals(role)) { this.role = Role.ADMINISTRATOR; }
        if("Пользователь".equals(role)) { this.role = Role.USER; }
    }

    @Override
    public boolean equals(Object o) {
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
