//package ru.girfanov.tm.servlet;
//
//import org.apache.log4j.Logger;
//import org.jetbrains.annotations.NotNull;
//import ru.girfanov.tm.entity.User;
//import ru.girfanov.tm.exception.EmptyFieldException;
//import ru.girfanov.tm.exception.UserNotFoundException;
//import ru.girfanov.tm.repository.UserRepository;
//import ru.girfanov.tm.service.UserService;
//import ru.girfanov.tm.util.LoggerUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/authorization")
//public class AuthorizationServlet extends HttpServlet {
//
//    @NotNull private static final Logger log = LoggerUtil.getLogger(AuthorizationServlet.class);
//
//    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/views/authorization.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        @NotNull final String login = req.getParameter("login");
//        @NotNull final String password = req.getParameter("password");
//        if(login.isEmpty() || password.isEmpty()) {
//            req.setAttribute("error", "login or password must be not empty");
//            resp.sendError(404);
//            return;
//        }
//        final User user = userService.findOneByLoginAndPassword(login, password);
//        if(user == null) {
//            req.setAttribute("error", "User does not exist");
//            resp.sendError(404);
//            return;
//        }
//        req.getSession().setAttribute("user", user);
//        req.getSession().setMaxInactiveInterval(-1);
//        resp.sendRedirect(req.getContextPath() + "/");
//        log.info("User " + user.getLogin() + " has signed in.");
//    }
//}
