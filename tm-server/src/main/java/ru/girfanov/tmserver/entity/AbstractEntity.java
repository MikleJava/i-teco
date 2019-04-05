package ru.girfanov.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 6459191137870691996L;

    @Getter
    @Setter
    @NotNull
    private String uuid = UUID.randomUUID().toString();

}
