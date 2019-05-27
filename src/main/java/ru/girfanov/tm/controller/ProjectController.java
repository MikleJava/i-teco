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
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.util.AdapterUtil;
import ru.girfanov.tm.util.LoggerUtil;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @NotNull
    private static final Logger log = LoggerUtil.getLogger(ProjectController.class);

    @Autowired
    private AdapterUtil<ProjectDto, Project> adapter;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Nullable
    private ProjectDto checkProject(final ModelMap modelMap, final String userId, final String projectId) throws UserNotFoundException {
        if (projectId.isEmpty()) {
            modelMap.addAttribute("error", "Project does not exist");
            return null;
        }
        @Nullable final ProjectDto projectDto = adapter.convertToEntityDto(projectService.findOne(userId, projectId));
        if (projectDto == null) {
            modelMap.addAttribute("error", "Project does not exist");
            return null;
        }
        return projectDto;
    }

    @GetMapping("/list")
    public String projectListView(final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        try {
            final List<ProjectDto> projects = adapter.convertToListEntitiesDto(projectService.findAllByUserId(userId));
            modelMap.addAttribute("projects", projects);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-list";
    }

    @GetMapping("/show")
    public String projectView(@RequestParam("project_id") final String projectId, final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        try {
            final ProjectDto projectDto = checkProject(modelMap, userId, projectId);
            if(projectDto == null) return "error";
            modelMap.addAttribute("project", projectDto);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-show";
    }

    @GetMapping("/create")
    public String createProjectView(final ModelMap modelMap) {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setDateStart(new Date());
        projectDto.setDateEnd(new Date());
        modelMap.addAttribute("project", projectDto);
        return "project-create";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") final ProjectDto projectDto, final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        projectDto.setUserId(userId);
        try {
            projectService.persist(userId, adapter.convertToEntity(projectDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has created project " + projectDto.getId());
        return "redirect:/project/show?project_id=" + projectDto.getId();
    }

    @GetMapping("/edit")
    public String editProjectView(@RequestParam("project_id") final String projectId, final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        try {
            final ProjectDto projectDto = checkProject(modelMap, userId, projectId);
            if(projectDto == null) return "error";
            modelMap.addAttribute("project", projectDto);
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        return "project-edit";
    }

    @PostMapping("/edit")
    public String editProject(@ModelAttribute("project") final ProjectDto projectDto, final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        try {
            projectService.merge(userId, adapter.convertToEntity(projectDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has edited project " + projectDto.getId());
        return "redirect:/project/show?project_id=" + projectDto.getId();
    }

    @PostMapping("/remove")
    public String removeProject(@RequestParam("project_id") final String projectId, final ModelMap modelMap, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        try {
            final ProjectDto projectDto = checkProject(modelMap, userId, projectId);
            if(projectDto == null) return "error";
            projectService.remove(userId, adapter.convertToEntity(projectDto));
        } catch (UserNotFoundException e) {
            modelMap.addAttribute("error", "User does not exist");
            return "error";
        }
        log.info("User " + userId + " has removed project " + projectId);
        return "redirect:/project/list";
    }

//    protected static ProjectDto castToProjectDto(@NotNull final Project project) {
//        final ProjectDto projectDto = new ProjectDto();
//        projectDto.setId(project.getId());
//        projectDto.setName(project.getName());
//        projectDto.setDescription(project.getDescription());
//        projectDto.setStatus(project.getStatus());
//        projectDto.setDateStart(project.getDateStart());
//        projectDto.setDateEnd(project.getDateEnd());
//        projectDto.setUserId(project.getUser().getId());
//        return projectDto;
//    }
//
//    protected Project castToProject(@NotNull final ProjectDto projectDto) {
//        final Project project = new Project();
//        project.setId(projectDto.getId());
//        project.setName(projectDto.getName());
//        project.setDescription(projectDto.getName());
//        project.setStatus(projectDto.getStatus());
//        project.setDateStart(projectDto.getDateStart());
//        project.setDateEnd(projectDto.getDateEnd());
//        project.setUser(userService.findOne(projectDto.getUserId()));
//        return project;
//    }
//
//    protected static List<ProjectDto> castToListProjectsDto(@NotNull final List<Project> projects) {
//        final List<ProjectDto> projectsDto = new ArrayList<>();
//        for (Project project : projects) {
//            projectsDto.add(castToProjectDto(project));
//        }
//        return projectsDto;
//    }
}
