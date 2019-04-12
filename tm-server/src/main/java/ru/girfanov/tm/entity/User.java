package ru.girfanov.tm.entity;

import lombok.*;
import ru.girfanov.tm.enumeration.Role;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -3316296014185311021L;

    @NonNull private String login;

    @NonNull private String password;

    @NonNull private Role role;
}
