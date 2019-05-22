package ru.girfanov.tm.dto;

import lombok.*;
import ru.girfanov.tm.enumeration.UserRoleEnum;

import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AbstractEntityDto{

    @NonNull private String login;

    @NonNull private String password;

    @Transient
    private String repassword;

    @NonNull private UserRoleEnum role;

}
