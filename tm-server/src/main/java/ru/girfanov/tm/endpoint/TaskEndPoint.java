package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public final class TaskEndPoint {

    @NonNull private ITaskService taskService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            if(sessionService.existSession(session)) taskService.persist(session.getUser().getId(), task);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            if(sessionService.existSession(session)) taskService.merge(session.getUser().getId(), task);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            if(sessionService.existSession(session)) taskService.remove(session.getUser().getId(), task);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "session") final Session session) {
        try {
            if(sessionService.existSession(session)) taskService.removeAllByUserId(session.getUser().getId());
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public Task findOneTask(@WebParam(name = "session") final Session session, @WebParam(name = "taskUuid") final String taskUuid) {
        try {
            if(sessionService.existSession(session)) return taskService.findOne(session.getUser().getId(), taskUuid);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTasks(@WebParam(name = "session") final Session session) {
        try {
            if(sessionService.existSession(session)) return taskService.findAllByUserId(session.getUser().getId());
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        try {
            if(sessionService.existSession(session)) return taskService.findAllTasksByProjectId(session.getUser().getId(), projectId);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        try {
            if(sessionService.existSession(session)) taskService.removeAllTasksByProjectId(session.getUser().getId(), projectId);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTasksSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        try {
            if(sessionService.existSession(session)) return taskService.findAllSortedByValue(session.getUser().getId(), value);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
