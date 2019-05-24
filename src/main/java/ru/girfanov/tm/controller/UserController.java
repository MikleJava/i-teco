package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.UserRoleEnum;
import ru.girfanov.tm.util.LoggerUtil;
import ru.girfanov.tm.validator.UserValidator;

import java.security.Principal;

@Controller
public class UserController {

    @NotNull private static final Logger log = LoggerUtil.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public ModelAndView registrationView(ModelMap modelMap) {
        final UserDto userDto = new UserDto();
        userDto.setRole(UserRoleEnum.USER);
        modelMap.addAttribute("user", userDto);
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if(bindingResult.hasErrors()) {
            return new ModelAndView("registration");
        }
        userService.persist(castToUser(userDto));
        log.info("User " + userDto.getLogin() + " has signed up");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/login")
    public ModelAndView authorizationView() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView authorization(@RequestParam("login") @NotNull final String login, final ModelMap modelMap) {
        @Nullable final UserDto userDto = castToUserDto(userService.findOneByLogin(login));
        if(userDto == null) {
            modelMap.addAttribute("error", "User does not exist");
            return new ModelAndView("error");
        }
         log.info("User " + userDto.getLogin() + " has signed in");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public ModelAndView logout(final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return new ModelAndView("error");
        }
        @Nullable final UserDto userDto = castToUserDto(userService.findOne(userId));
        if(userDto == null) {
            modelMap.addAttribute("error", "User does not exist");
            return new ModelAndView("error");
        }
        log.info("User " + userDto.getLogin() + " has logged out");
        return new ModelAndView("redirect:/");
    }

    protected static UserDto castToUserDto(@NotNull final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private User castToUser(@NotNull final UserDto userDto) {
        final User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
