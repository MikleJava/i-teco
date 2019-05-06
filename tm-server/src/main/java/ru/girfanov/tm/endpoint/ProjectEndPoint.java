package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Component
@WebService
@NoArgsConstructor
public class ProjectEndPoint {

    @Autowired
    private IProjectService projectService;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUserService userService;

    @WebMethod
    public void persistProject(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "project") final ProjectDto projectDto) {
        try {
            if(sessionService.existSession(sessionDto)) projectService.persist(userService.findOne(sessionDto.getUserId()), castToProject(projectDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "project") final ProjectDto projectDto) {
        try {
            if(sessionService.existSession(sessionDto)) projectService.merge(userService.findOne(sessionDto.getUserId()), castToProject(projectDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "project") final ProjectDto projectDto) {
        try {
            if(sessionService.existSession(sessionDto)) projectService.remove(userService.findOne(sessionDto.getUserId()), castToProject(projectDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            if(sessionService.existSession(sessionDto)) projectService.removeAllByUserId(userService.findOne(sessionDto.getUserId()));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public ProjectDto findOneProject(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "projectUuid") final String projectUuid) {
        try {
            if(sessionService.existSession(sessionDto)) return castToProjectDto(projectService.findOne(userService.findOne(sessionDto.getUserId()), projectUuid));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<ProjectDto> findAllProjects(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            if(sessionService.existSession(sessionDto)) return castToListProjectsDto(projectService.findAllByUserId(userService.findOne(sessionDto.getUserId())));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    @Nullable
//    @WebMethod
//    public List<ProjectDto> findAllProjectsSortedByValue(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "value") final String value) {
//        try {
//            if(sessionService.existSession(sessionDto)) return castToListProjectsDto(projectService.findAllSortedByValue(userService.findOne(sessionDto.getUserId()), value));
//        } catch (WrongSessionException | UserNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

    private ProjectDto castToProjectDto(@NotNull final Project project) {
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

    private Project castToProject(@NotNull final ProjectDto projectDto) {
        final Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getName());
        project.setStatus(projectDto.getStatus());
        project.setDateStart(projectDto.getDateStart());
        project.setDateEnd(projectDto.getDateEnd());
        try {
            project.setUser(userService.findOne(projectDto.getUserId()));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return project;
    }

    private List<ProjectDto> castToListProjectsDto(@NotNull final List<Project> projects) {
        final List<ProjectDto> projectsDto = new ArrayList<>();
        for (Project project : projects) {
            projectsDto.add(castToProjectDto(project));
        }
        return projectsDto;
    }
}
