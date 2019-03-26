package ru.girfanov.tm.entity;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractEntity {

    public AbstractEntity() {
    }

    @NotNull
    private String uuid = UUID.randomUUID().toString();

    @NotNull
    public String getUuid() {
        return uuid;
    }
}
