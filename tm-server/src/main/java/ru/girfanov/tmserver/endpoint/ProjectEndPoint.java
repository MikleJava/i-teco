package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.IProjectService;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.entity.Project;
import ru.girfanov.tmserver.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class ProjectEndPoint {

    @NonNull private IProjectService projectService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) projectService.persist(session.getUserId(), project);
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) projectService.merge(session.getUserId(), project);
    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final Session session, @WebParam(name = "projectUuid") final String projectUuid) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) projectService.remove(session.getUserId(), projectUuid);
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) projectService.removeAll(session.getUserId());
    }

    @WebMethod
    public Project findOneProject(@WebParam(name = "session") final Session session, @WebParam(name = "projectUuid") final String projectUuid) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return projectService.findOne(session.getUserId(), projectUuid);
    }

    @WebMethod
    public List<Project> findAllProjects(@WebParam(name = "session") final Session session) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return projectService.findAll(session.getUserId());
    }

    @WebMethod
    public List<Project> findAllProjectsSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return projectService.findAllSortedByValue(session.getUserId(), value);
    }
}
