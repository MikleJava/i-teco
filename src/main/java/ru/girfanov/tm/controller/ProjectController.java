package ru.girfanov.tm.controller;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.service.ProjectService;
import ru.girfanov.tm.service.UserService;
import ru.girfanov.tm.util.LoggerUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @NotNull
    private static final Logger log = LoggerUtil.getLogger(ProjectController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

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
    private Project checkProject(final HttpServletRequest request, final ModelMap modelMap, final String userId, final String projectId) throws UserNotFoundException {
        if (projectId.isEmpty()) {
            modelMap.addAttribute("error", "Project does not exist");
            return null;
        }
        @Nullable final Project project = projectService.findOne(userId, projectId);
        if (project == null) {
            modelMap.addAttribute("error", "Project does not exist");
            return null;
        }
        return project;
    }

    @GetMapping("/list")
    public String projectListView(final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final List<Project> projects = projectService.findAllByUserId(userId);
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-list";
    }

    @GetMapping("/show")
    public String projectView(@RequestParam("project_id") final String projectId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final Project project = checkProject(request, modelMap, userId, projectId);
            if(project == null) return "error";
            modelMap.addAttribute("project", project);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-show";
    }

    @GetMapping("/create")
    public String createProjectView(final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        final Project project = new Project();
        project.setDateStart(new Date());
        project.setDateEnd(new Date());
        modelMap.addAttribute("project", project);
        return "project-create";
    }

    @PostMapping("/create")
    public String createProject(final HttpServletRequest request, @ModelAttribute("project") final Project project, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        project.setUser(userService.findOne(userId));
        try {
            projectService.persist(userId, project);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has created project " + project.getId());
        return "redirect:/project/show?project_id=" + project.getId();
    }

    @GetMapping("/edit")
    public String editProjectView(@RequestParam("project_id") final String projectId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final Project project = checkProject(request, modelMap, userId, projectId);
            if(project == null) return "error";
            modelMap.addAttribute("project", project);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-edit";
    }

    @PostMapping("/edit")
    public String editProject(@ModelAttribute("project") final Project project, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            projectService.merge(userId, project);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has edited project " + project.getId());
        return "redirect:/project/show?project_id=" + project.getId();
    }

    @PostMapping("/remove")
    public String removeProject(@RequestParam("project_id") final String projectId, final HttpServletRequest request, final ModelMap modelMap) {
        final String userId = checkUser(request, modelMap);
        if(userId.equals("error")) return "error";
        try {
            final Project project = checkProject(request, modelMap, userId, projectId);
            if(project == null) return "error";
            projectService.remove(userId, project);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has removed project " + projectId);
        return "redirect:/project/list";
    }
}
