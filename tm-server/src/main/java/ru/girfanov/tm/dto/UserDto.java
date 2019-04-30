package ru.girfanov.tm.dto;

import lombok.*;
import ru.girfanov.tm.enumeration.Role;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class UserDto extends AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = -3316296014185311021L;

    @NonNull private String login;

    @NonNull private String password;

    @NonNull private Role role;
}
