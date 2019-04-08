package ru.girfanov.tm.enumeration;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public enum Status {

    PLANNING("Запланировано"), PROCESS("В процессе"), READY("Готово");

    @NonNull
    private String displayName;
}
