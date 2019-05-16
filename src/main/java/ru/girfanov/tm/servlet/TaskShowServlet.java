//package ru.girfanov.tm.servlet;
//
//import org.apache.log4j.Logger;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import ru.girfanov.tm.entity.Task;
//import ru.girfanov.tm.entity.User;
//import ru.girfanov.tm.exception.UserNotFoundException;
//import ru.girfanov.tm.repository.TaskRepository;
//import ru.girfanov.tm.repository.UserRepository;
//import ru.girfanov.tm.service.TaskService;
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
//@WebServlet("/task-show")
//public class TaskShowServlet extends HttpServlet {
//
//    @NotNull
//    private static final Logger log = LoggerUtil.getLogger(TaskShowServlet.class);
//
//    @NotNull private final UserService userService = new UserService(UserRepository.getInstance());
//    @NotNull private final TaskService taskService = new TaskService(UserRepository.getInstance(), TaskRepository.getInstance());
//
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
//        @NotNull final String taskId = req.getParameter("task_id");
//        if(taskId.isEmpty()) {
//            req.setAttribute("error", "Task does not exist");
//            resp.sendError(404);
//            return;
//        }
//        try {
//            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
//            if (task == null) {
//                req.setAttribute("error", "Task does not exist");
//                resp.sendError(404);
//                return;
//            }
//            req.setAttribute("task", task);
//            req.getRequestDispatcher("/WEB-INF/views/task-show.jsp").forward(req, resp);
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
