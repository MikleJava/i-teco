package ru.girfanov.tm.servlet;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    @NotNull private static final Logger log = LoggerUtil.getLogger(ProjectEditServlet.class);

    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
    @NotNull private final TaskService taskService = new TaskService(UserRepository.getInstance(), TaskRepository.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            @Nullable final User user = userService.findOne(((User) req.getSession().getAttribute("user")).getId());
            if(user == null) return;
            @Nullable final Task task = taskService.findOne(user.getId(), req.getParameter("task_id"));
            if(task == null) return;
            taskService.remove(user.getId(), task);
            resp.sendRedirect(req.getContextPath() + "/task-list");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}