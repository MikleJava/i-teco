package ru.girfanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = 6459191137870691996L;

    @NotNull private String id = UUID.randomUUID().toString();

}
