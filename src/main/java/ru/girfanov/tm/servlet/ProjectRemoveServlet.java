package ru.girfanov.tm.servlet;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
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

@WebServlet("/project-remove")
public class ProjectRemoveServlet extends HttpServlet {

    @NotNull private static final Logger log = LoggerUtil.getLogger(ProjectEditServlet.class);

    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
    @NotNull private final ProjectService projectService = new ProjectService(UserRepository.getInstance(), ProjectRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            @Nullable final User user = userService.findOne(((User) req.getSession().getAttribute("user")).getId());
            if(user == null) return;
            @Nullable final Project project = projectService.findOne(user.getId(), req.getParameter("project_id"));
            if(project == null) return;
            projectService.remove(user.getId(), project);
            resp.sendRedirect(req.getContextPath() + "/project-list");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
