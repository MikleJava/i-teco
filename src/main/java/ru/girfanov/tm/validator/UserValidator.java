package ru.girfanov.tm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.User;

@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final UserDto userDto = (UserDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required.field");
        if(userDto.getLogin().length() < 4 || userDto.getLogin().length() > 20) {
            errors.rejectValue("login", "Size.login");
        }
        if(userService.findOneByLogin(userDto.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.login");
        }
        if(userDto.getPassword().length() < 4) {
            errors.rejectValue("password", "Size.password");
        }
    }
}
