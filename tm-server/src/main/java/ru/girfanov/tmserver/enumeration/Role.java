package ru.girfanov.tmserver.enumeration;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public enum Role {

    ADMINISTRATOR("Администратор"), USER("Пользователь");

    @NonNull
    private String displayName;
}
