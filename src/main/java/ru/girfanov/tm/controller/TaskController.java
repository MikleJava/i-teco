package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.dto.TaskDto;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.TaskService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @NotNull private static final Logger log = LoggerUtil.getLogger(TaskController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    private String checkUser(final HttpServletRequest request, final ModelMap modelMap) {
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
        return userId;
    }

    @Nullable
    private TaskDto checkTask(final HttpServletRequest request, final ModelMap modelMap, final String userId, final String taskId) throws UserNotFoundException {
        if (taskId.isEmpty()) {
            modelMap.addAttribute("error", "Task does not exist");
            return null;
        }
        @Nullable final TaskDto taskDto = castToTaskDto(taskService.findOne(userId, taskId));
        if (taskDto == null) {
            modelMap.addAttribute("error", "Task does not exist");
            return null;
        }
        return taskDto;
    }

    @GetMapping("/list")
    public String taskListView(final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final List<TaskDto> tasks = castToListTasksDto(taskService.findAllByUserId(userId));
            modelMap.addAttribute("tasks", tasks);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-list";
    }

    @GetMapping("/show")
    public String taskView(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final TaskDto taskDto = checkTask(request, modelMap, userId, taskId);
            if(taskDto == null) return "error";
            modelMap.addAttribute("task", taskDto);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-show";
    }

    @GetMapping("/create")
    public String createTaskView(final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        final TaskDto taskDto = new TaskDto();
        taskDto.setDateStart(new Date());
        taskDto.setDateEnd(new Date());
        modelMap.addAttribute("task", taskDto);
        try {
            final List<ProjectDto> projects = ProjectController.castToListProjectsDto(projectService.findAllByUserId(userId));
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-create";
    }

    @PostMapping("/create")
    public String createTask(final HttpServletRequest request, @ModelAttribute("task") final TaskDto taskDto, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        taskDto.setUserId(userId);
        try {
            taskService.persist(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has created task " + taskDto.getId());
        return "redirect:/task/show?task_id=" + taskDto.getId();
    }

    @GetMapping("/edit")
    public String editTaskView(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final TaskDto taskDto = checkTask(request, modelMap, userId, taskId);
            if(taskDto == null) return "error";
            modelMap.addAttribute("task", taskDto);
            final List<ProjectDto> projects = ProjectController.castToListProjectsDto(projectService.findAllByUserId(userId));
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "task-edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute("task") final TaskDto taskDto, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            taskService.merge(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has edited task " + taskDto.getId());
        return "redirect:/task/show?task_id=" + taskDto.getId();
    }

    @PostMapping("/remove")
    public String removeTask(@RequestParam("task_id") final String taskId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";

        try {
            final TaskDto taskDto = checkTask(request, modelMap, userId, taskId);
            if(taskDto == null) return "error";
            taskService.remove(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has removed task " + taskId);
        return "redirect:/task/list";
    }

    private TaskDto castToTaskDto(@NotNull final Task task) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setDateStart(task.getDateStart());
        taskDto.setDateEnd(task.getDateEnd());
        taskDto.setProjectId(task.getProject().getId());
        taskDto.setUserId(task.getUser().getId());
        return taskDto;
    }

    private Task castToTask(@NotNull final TaskDto taskDto) {
        final Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDateStart(taskDto.getDateStart());
        task.setDateEnd(taskDto.getDateEnd());
        try {
            task.setUser(userService.findOne(taskDto.getUserId()));
            task.setProject(projectService.findOne(taskDto.getUserId(), taskDto.getProjectId()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return task;
    }

    private List<TaskDto> castToListTasksDto(@NotNull final List<Task> tasks) {
        final List<TaskDto> tasksDto = new ArrayList<>();
        for (Task task : tasks) {
            tasksDto.add(castToTaskDto(task));
        }
        return tasksDto;
    }
}
