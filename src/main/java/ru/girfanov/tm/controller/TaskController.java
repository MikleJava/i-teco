package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @NotNull private static final Logger log = LoggerUtil.getLogger(TaskController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public String taskListView(final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        try {
            final List<Task> tasks = taskService.findAllByUserId(userId);
            modelMap.addAttribute("tasks", tasks);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-list";
    }

    @GetMapping("/show")
    public String taskView(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        if (taskId.isEmpty()) {
            modelMap.addAttribute("error", "Task does not exist");
            return "error";
        }
        try {
            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
            if (task == null) {
                modelMap.addAttribute("error", "Task does not exist");
                return "error";
            }
            modelMap.addAttribute("task", task);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-show";
    }

    @GetMapping("/create")
    public String createTaskView(final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        final Task task = new Task();
        task.setDateStart(new Date());
        task.setDateEnd(new Date());
        modelMap.addAttribute("task", task);
        try {
            final List<Project> projects = projectService.findAllByUserId(userId);
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-create";
    }

    @PostMapping("/create")
    public String createTask(final HttpServletRequest request, @ModelAttribute("task") final Task task, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        task.setUserId(userId);
        try {
            taskService.persist(user.getId(), task);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + user.getLogin() + " has created task " + task.getId());
        return "redirect:/task/show?task_id=" + task.getId();
    }

    @GetMapping("/edit")
    public String editTaskView(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        if (taskId.isEmpty()) {
            modelMap.addAttribute("error", "Task does not exist");
            return "error";
        }
        try {
            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
            if (task == null) {
                modelMap.addAttribute("error", "Task does not exist");
                return "error";
            }
            modelMap.addAttribute("task", task);
            final List<Project> projects = projectService.findAllByUserId(userId);
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute("task") final Task task, final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        try {
            taskService.merge(user.getId(), task);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + user.getLogin() + " has edited task " + task.getId());
        return "redirect:/task/show?task_id=" + task.getId();
    }

    @PostMapping("/remove")
    public String removeTask(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        @Nullable final String userId = (String) request.getSession().getAttribute("user_id");
        if (userId == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        @Nullable final User user = userService.findOne(userId);
        if (user == null) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        if (taskId.isEmpty()) {
            modelMap.addAttribute("error", "Task does not exist");
            return "error";
        }
        try {
            @Nullable final Task task = taskService.findOne(user.getId(), taskId);
            if (task == null) {
                modelMap.addAttribute("error", "Task does not exist");
                return "error";
            }
            taskService.remove(user.getId(), task);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + user.getLogin() + " has removed task " + taskId);
        return "redirect:/task/list";
    }
}
