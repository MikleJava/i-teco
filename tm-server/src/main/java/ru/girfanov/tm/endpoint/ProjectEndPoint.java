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
    public void persistProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        projectService.persist(session.getUser().getId(), project);
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        projectService.merge(session.getUser().getId(), project);
    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        projectService.remove(session.getUser().getId(), project);
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        projectService.removeAllByUserId(session.getUser().getId());
    }

    @WebMethod
    public Project findOneProject(@WebParam(name = "session") final Session session, @WebParam(name = "projectUuid") final String projectUuid) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return projectService.findOne(session.getUser().getId(), projectUuid);
    }

    @WebMethod
    public List<Project> findAllProjects(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return projectService.findAllByUserId(session.getUser().getId());
    }

    @WebMethod
    public List<Project> findAllProjectsSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return projectService.findAllSortedByValue(session.getUser().getId(), value);
    }
}
