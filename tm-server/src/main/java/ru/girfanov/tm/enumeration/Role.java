package ru.girfanov.tm.enumeration;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public enum Role {

    ADMIN("Администратор"), USER("Пользователь");

    @NonNull
    private String displayName;
}
