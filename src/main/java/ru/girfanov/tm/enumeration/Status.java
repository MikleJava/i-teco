package ru.girfanov.tm.enumeration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public enum Status {

    PLANNING("Запланировано"), PROCESS("В процессе"), READY("Готово");

    @NonNull
    private String name;
}
