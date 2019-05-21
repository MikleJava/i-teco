package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @NotNull private static final Logger log = LoggerUtil.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/registration")
    public String registrationView() { return "registration"; }

    @PostMapping("/registration")
    public String registration(@RequestParam("login") @NotNull final String login, @RequestParam("password") @NotNull final String password, ModelMap modelMap) {
        if(login.isEmpty() || password.isEmpty()) {
            modelMap.addAttribute("error", "Login and Password must be not empty");
            return "error";
        }
        final UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);
        userService.persist(castToUser(userDto));
        log.info("User " + userDto.getLogin() + " has signed up");
        return "redirect:/";
    }

    @GetMapping("/authorization")
    public String authorizationView() {
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@RequestParam("login") @NotNull final String login, @RequestParam("password") @NotNull final String password, @NotNull final HttpServletRequest request, final ModelMap modelMap) {
        if(login.isEmpty() || password.isEmpty()) {
            modelMap.addAttribute("error", "Login and Password must be not empty");
            return "error";
        }
        @Nullable final UserDto userDto = castToUserDto(userService.findOneByLogin(login));
        if(userDto == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        request.getSession().setAttribute("user_id", userDto.getId());
        request.getSession().setMaxInactiveInterval(-1);
        log.info("User " + userDto.getLogin() + " has signed in");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(@NotNull final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if(userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final UserDto userDto = castToUserDto(userService.findOne(userId));
        if(userDto == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        request.getSession().invalidate();
        log.info("User " + userDto.getLogin() + " has logged out");
        return "redirect:/";
    }

    private UserDto castToUserDto(@NotNull final User user) {
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
