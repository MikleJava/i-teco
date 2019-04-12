package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public final class ProjectEndPoint {

    @NonNull private IProjectService projectService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) throws WrongSessionException {
        sessionService.existSession(session);
        projectService.persist(session.getUserId(), project);
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) throws WrongSessionException {
        sessionService.existSession(session);
        projectService.merge(session.getUserId(), project);
    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) throws WrongSessionException {
        sessionService.existSession(session);
        projectService.remove(session.getUserId(), project);
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        projectService.removeAllByUserId(session.getUserId());
    }

    @WebMethod
    public Project findOneProject(@WebParam(name = "session") final Session session, @WebParam(name = "projectUuid") final String projectUuid) throws WrongSessionException {
        sessionService.existSession(session);
        return projectService.findOne(session.getUserId(), projectUuid);
    }

    @WebMethod
    public List<Project> findAllProjects(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        return projectService.findAllByUserId(session.getUserId());
    }

    @WebMethod
    public List<Project> findAllProjectsSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) throws WrongSessionException {
        sessionService.existSession(session);
        return projectService.findAllSortedByValue(session.getUserId(), value);
    }
}
