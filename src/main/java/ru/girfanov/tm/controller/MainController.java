package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.girfanov.tm.util.LoggerUtil;


@Controller
public class MainController {

    @NotNull
    private static final Logger log = LoggerUtil.getLogger(MainController.class);

    @GetMapping("/")
    public ModelAndView mainView() {
        return new ModelAndView("main");
    }

}
