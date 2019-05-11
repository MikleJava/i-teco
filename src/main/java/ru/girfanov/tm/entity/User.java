package ru.girfanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity{
    private String login;
    private String password;
}
