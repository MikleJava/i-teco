package ru.girfanov.tm.entity;

import java.util.UUID;

public abstract class AbstractEntity {

    public AbstractEntity() {
    }

    private String uuid = UUID.randomUUID().toString();

    public String getUuid() {
        return uuid;
    }
}
