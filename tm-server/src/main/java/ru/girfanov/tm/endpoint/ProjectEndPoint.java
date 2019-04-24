package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.UserNotFoundException;
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
            if(sessionService.existSession(session)) projectService.persist(session.getUser().getId(), project);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void mergeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        try {
            if(sessionService.existSession(session)) projectService.merge(session.getUser().getId(), project);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeProject(@WebParam(name = "session") final Session session, @WebParam(name = "project") final Project project) {
        try {
            if(sessionService.existSession(session)) projectService.remove(session.getUser().getId(), project);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeAllProjects(@WebParam(name = "session") final Session session) {
        try {
            if(sessionService.existSession(session)) projectService.removeAllByUserId(session.getUser().getId());
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public Project findOneProject(@WebParam(name = "session") final Session session, @WebParam(name = "projectUuid") final String projectUuid) {
        try {
            if(sessionService.existSession(session)) return projectService.findOne(session.getUser().getId(), projectUuid);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<Project> findAllProjects(@WebParam(name = "session") final Session session) {
        try {
            if(sessionService.existSession(session)) return projectService.findAllByUserId(session.getUser().getId());
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<Project> findAllProjectsSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        try {
            if(sessionService.existSession(session)) return projectService.findAllSortedByValue(session.getUser().getId(), value);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
