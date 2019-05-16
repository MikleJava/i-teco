package ru.girfanov.tm.servlet;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static ru.girfanov.tm.util.DateFormatUtil.getDateISO8601;

@WebServlet("/task-edit")
public class TaskEditServlet extends HttpServlet {

    @NotNull private static final Logger log = LoggerUtil.getLogger(TaskEditServlet.class);

    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
    @NotNull private final TaskService taskService = new TaskService(UserRepository.getInstance(), TaskRepository.getInstance());
    @NotNull private final ProjectService projectService = new ProjectService(UserRepository.getInstance(), ProjectRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        @NotNull final String taskId = req.getParameter("task_id");
        if(taskId.isEmpty()) {
            req.setAttribute("error", "Task does not exist");
            resp.sendError(404);
            return;
        }
        try {
            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
            if(task == null) {
                req.setAttribute("error", "Task does not exist");
                resp.sendError(404);
                return;
            }
            req.setAttribute("task", task);
            req.setAttribute("projects", projectService.findAllByUserId(user.getId()));
            req.getRequestDispatcher("/WEB-INF/views/task-edit.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("error", "User does not exist");
            resp.sendError(404);
        }
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
        @NotNull final String taskId = req.getParameter("task_id");
        if(taskId.isEmpty()) {
            req.setAttribute("error", "Task does not exist");
            resp.sendError(404);
            return;
        }
        try {
            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
            if(task == null) {
                req.setAttribute("error", "Task does not exist");
                resp.sendError(404);
                return;
            }
            @Nullable final String projectId = req.getParameter("project_id");
            if(projectId == null) {
                req.setAttribute("error", "Project does not exist");
                resp.sendError(404);
                return;
            }
            task.setName(req.getParameter("name"));
            task.setDescription(req.getParameter("desc"));
            task.setStatus(Status.valueOf(req.getParameter("status")));
            task.setDateStart(getDateISO8601(req.getParameter("date-start")));
            task.setDateEnd(getDateISO8601(req.getParameter("date-end")));
            task.setUserId(user.getId());
            task.setProjectId(projectId);
            taskService.merge(user.getId(), task);
            resp.sendRedirect(req.getContextPath() + "/task-show?task_id=" + req.getParameter("task_id"));
        } catch (UserNotFoundException unfe) {
            req.setAttribute("error", "User does not exist");
            resp.sendError(404);
        } catch (ParseException pe) {
            req.setAttribute("error", "Incorrect date");
            resp.sendError(404);
        }
    }
}

