package ru.girfanov.tm.servlet;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class MainServlet extends HttpServlet {

    @NotNull
    private final UserService userService = new UserService(UserRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }
}
