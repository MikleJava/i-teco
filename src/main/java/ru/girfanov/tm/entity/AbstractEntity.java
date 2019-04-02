package ru.girfanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -7157244917931848282L;

    @Getter
    @NotNull
    private String uuid = UUID.randomUUID().toString();

}
