package ru.girfanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntityDto {
    @NotNull
    private String id = UUID.randomUUID().toString();
}
