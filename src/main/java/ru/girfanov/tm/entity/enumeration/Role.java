package ru.girfanov.tm.entity.enumeration;

public enum Role {

    ADMINISTRATOR("Администратор"), USER("Пользователь");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }
}
