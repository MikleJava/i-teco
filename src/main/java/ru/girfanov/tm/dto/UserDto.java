package ru.girfanov.tm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AbstractEntityDto{

    @NonNull private String login;
    @NonNull private String password;

}
