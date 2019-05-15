package ru.girfanov.tm.servlet;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static ru.girfanov.tm.util.DateFormatUtil.*;

@WebServlet("/project-create")
public class ProjectCreateServlet extends HttpServlet {

    @NotNull private static final Logger log = LoggerUtil.getLogger(ProjectCreateServlet.class);

    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
    @NotNull private final ProjectService projectService = new ProjectService(UserRepository.getInstance(), ProjectRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/project-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @Nullable User user = (User) req.getSession().getAttribute("user");
        if(user == null) {
            req.setAttribute("error", "User does not exist");
            resp.sendError(404);
            return;
        }
        user = userService.findOne(user.getId());
        if(user == null) {
            req.setAttribute("error", "User does not exist");
            resp.sendError(404);
            return;
        }
        final Project project = new Project();
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("desc"));
        project.setStatus(Status.valueOf(req.getParameter("status")));
        try {
            project.setDateStart(getDateISO8601(req.getParameter("date-start")));
            project.setDateEnd(getDateISO8601(req.getParameter("date-end")));
        } catch (ParseException e) {
            req.setAttribute("error", "Incorrect date");
            resp.sendError(404);
            return;
        }
        project.setUserId(user.getId());
        projectService.persist(user.getId(), project);
        resp.sendRedirect(req.getContextPath() + "/project-list");
    }
}
