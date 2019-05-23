package ru.girfanov.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectControllerRest {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProjectDto> show(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ProjectDto projectDto;
        try {
            projectDto = castToProjectDto(projectService.findOne(userId, id));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProjectDto>> list(final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(userId==null || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<ProjectDto> projectsDto;
        try {
            projectsDto = castToListProjectsDto(projectService.findAllByUserId(userId));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProjectDto> create(@RequestBody final ProjectDto projectDto, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(projectDto == null || userId == null || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            projectService.persist(userId, castToProject(projectDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProjectDto> update(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ProjectDto projectDto;
        try {
            projectDto = castToProjectDto(projectService.findOne(userId, id));
            projectService.merge(userId, castToProject(projectDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProjectDto> delete(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ProjectDto projectDto;
        try {
            projectDto = castToProjectDto(projectService.findOne(userId, id));
            projectService.remove(userId, castToProject(projectDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected static ProjectDto castToProjectDto(@NotNull final Project project) {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setStatus(project.getStatus());
        projectDto.setDateStart(project.getDateStart());
        projectDto.setDateEnd(project.getDateEnd());
        projectDto.setUserId(project.getUser().getId());
        return projectDto;
    }

    protected Project castToProject(@NotNull final ProjectDto projectDto) {
        final Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getName());
        project.setStatus(projectDto.getStatus());
        project.setDateStart(projectDto.getDateStart());
        project.setDateEnd(projectDto.getDateEnd());
        project.setUser(userService.findOne(projectDto.getUserId()));
        return project;
    }

    protected static List<ProjectDto> castToListProjectsDto(@NotNull final List<Project> projects) {
        final List<ProjectDto> projectsDto = new ArrayList<>();
        for (Project project : projects) {
            projectsDto.add(castToProjectDto(project));
        }
        return projectsDto;
    }
}
