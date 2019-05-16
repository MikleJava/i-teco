//package ru.girfanov.tm.servlet;
//
//import org.apache.log4j.Logger;
//import org.jetbrains.annotations.NotNull;
//import ru.girfanov.tm.entity.User;
//import ru.girfanov.tm.util.LoggerUtil;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/logout")
//public class LogoutServlet extends HttpServlet {
//
//    @NotNull
//    private static final Logger log = LoggerUtil.getLogger(LogoutServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        final User user = ((User) req.getSession().getAttribute("user"));
//        req.getSession().invalidate();
//        resp.sendRedirect(req.getContextPath() + "/");
//        log.info("User " + user.getLogin() + " has logged out");
//    }
//}
