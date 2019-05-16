package ru.girfanov.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registrationView() { return "registration"; }

    @PostMapping("/registration")
    public String registration(@RequestParam("login") @NotNull final String login, @RequestParam("password") @NotNull final String password, @NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response) {
        if(login.isEmpty() || password.isEmpty()) { return "error"; }
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userService.persist(user);
        return "main";
    }

    @GetMapping("/authorization")
    public String authorizationView() {
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@RequestParam("login") @NotNull final String login, @RequestParam("password") @NotNull final String password, @NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response) {
        if(login.isEmpty() || password.isEmpty()) { return "error"; }
        @Nullable final User user = userService.findOneByLoginAndPassword(login, password);
        if(user == null) { return "error"; }
        request.getSession().setAttribute("user_id", user.getId());
        request.getSession().setMaxInactiveInterval(-1);
        return "main";
    }
}
