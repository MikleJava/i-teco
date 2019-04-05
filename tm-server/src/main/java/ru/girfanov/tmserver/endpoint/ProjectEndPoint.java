package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.IProjectService;
import ru.girfanov.tmserver.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class ProjectEndPoint {

    @NonNull private IProjectService projectService;

    @WebMethod
    public void persistProject(@WebParam(name = "userId") final String userId, @WebParam(name = "project") final Project project) {
        projectService.persist(userId, project);
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "userId") final String userId, @WebParam(name = "project") final Project project) {
        projectService.merge(userId, project);
    }

    @WebMethod
    public void removeProject(@WebParam(name = "userId") final String userId, @WebParam(name = "projectUuid") final String projectUuid) {
        projectService.remove(userId, projectUuid);
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "userId") final String userId) {
        projectService.removeAll(userId);
    }

    @WebMethod
    public Project findOneProject(@WebParam(name = "userId") final String userId, @WebParam(name = "projectUuid") final String projectUuid) {
        return projectService.findOne(userId, projectUuid);
    }

    @WebMethod
    public List<Project> findAllProjects(@WebParam(name = "userId") final String userId) {
        return projectService.findAll(userId);
    }

    @WebMethod
    public List<Project> findAllProjectsSortedByValue(@WebParam(name = "userId") final String userId, @WebParam(name = "value") final String value) {
        return projectService.findAllSortedByValue(userId, value);
    }
}
