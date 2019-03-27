package ru.girfanov.tm.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"uuid"})
public abstract class AbstractEntity {

    @NotNull
    private String uuid = UUID.randomUUID().toString();

}
