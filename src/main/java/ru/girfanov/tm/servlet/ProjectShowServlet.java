//package ru.girfanov.tm.servlet;
//
//import org.apache.log4j.Logger;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import ru.girfanov.tm.entity.Project;
//import ru.girfanov.tm.entity.User;
//import ru.girfanov.tm.exception.UserNotFoundException;
//import ru.girfanov.tm.repository.ProjectRepository;
//import ru.girfanov.tm.repository.UserRepository;
//import ru.girfanov.tm.service.ProjectService;
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
//@WebServlet("/project-show")
//public class ProjectShowServlet extends HttpServlet {
//
//    @NotNull
//    private static final Logger log = LoggerUtil.getLogger(ProjectShowServlet.class);
//
//    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
//    @NotNull private final ProjectService projectService = new ProjectService(UserRepository.getInstance(), ProjectRepository.getInstance());
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        @Nullable User user = (User) req.getSession().getAttribute("user");
//        if(user == null) {
//            req.setAttribute("error", "User does not exist");
//            resp.sendError(404);
//            return;
//        }
//        user = userService.findOne(user.getId());
//        if(user == null) {
//            req.setAttribute("error", "User does not exist");
//            resp.sendError(404);
//            return;
//        }
//        @NotNull final String projectId = req.getParameter("project_id");
//        if(projectId.isEmpty()) {
//            req.setAttribute("error", "Project does not exist");
//            resp.sendError(404);
//            return;
//        }
//        try {
//            @Nullable final Project project = projectService.findOne(user.getId(), projectId);
//            if(project == null) {
//                req.setAttribute("error", "Project does not exist");
//                resp.sendError(404);
//                return;
//            }
//            req.setAttribute("project", project);
//            req.getRequestDispatcher("/WEB-INF/views/project-show.jsp").forward(req, resp);
//        } catch (UserNotFoundException e) {
//            req.setAttribute("error", "User does not exist");
//            resp.sendError(404);
//        }
//    }
//}
