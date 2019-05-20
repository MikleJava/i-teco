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
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userService.persist(user);
        log.info("User " + user.getLogin() + " has signed up");
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
        @Nullable final User user = userService.findOneByLogin(login);
        if(user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        request.getSession().setAttribute("user_id", user.getId());
        request.getSession().setMaxInactiveInterval(-1);
        log.info("User " + user.getLogin() + " has signed in");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(@NotNull final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if(userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if(user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        request.getSession().invalidate();
        log.info("User " + user.getLogin() + " has logged out");
        return "redirect:/";
    }
}
