package ru.girfanov.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Session implements Serializable {

    private static final long serialVersionUID = -4054345421899926821L;

    private String signature;

}
