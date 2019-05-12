package ru.girfanov.tm.servlet;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        userService.persist(user);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
