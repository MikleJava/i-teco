package ru.girfanov.tm.entity.enumeration;

import org.jetbrains.annotations.NotNull;

public enum Role {

    ADMINISTRATOR("Администратор"), USER("Пользователь");

    @NotNull
    private String displayName;

    Role(@NotNull String displayName) {
        this.displayName = displayName;
    }
}
