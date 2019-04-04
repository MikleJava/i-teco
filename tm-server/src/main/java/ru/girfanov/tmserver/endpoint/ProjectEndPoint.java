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
    public void persist(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final Project entity) {
        projectService.persist(userId, entity);
    }

    @WebMethod
    public void merge(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final Project entity) {
        projectService.merge(userId, entity);
    }

    @WebMethod
    public void remove(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        projectService.remove(userId, uuid);
    }

    @WebMethod
    public void removeAll(@WebParam(name = "userId") final String userId) {
        projectService.removeAll(userId);
    }

    @WebMethod
    public Project findOne(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        return projectService.findOne(userId, uuid);
    }

    @WebMethod
    public List<Project> findAll(@WebParam(name = "userId") final String userId) {
        return projectService.findAll(userId);
    }

    @WebMethod
    public List<Project> findAllSortedByValue(@WebParam(name = "userId") final String userId, @WebParam(name = "value") final String value) {
        return projectService.findAllSortedByValue(userId, value);
    }
}
