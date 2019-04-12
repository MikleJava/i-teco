package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.Task;
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
    public void persistTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) throws WrongSessionException {
        sessionService.existSession(session);
        taskService.persist(session.getUserId(), task);
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) throws WrongSessionException {
        sessionService.existSession(session);
        taskService.merge(session.getUserId(), task);
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) throws WrongSessionException {
        sessionService.existSession(session);
        taskService.remove(session.getUserId(), task);
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        taskService.removeAllByUserId(session.getUserId());
    }

    @WebMethod
    public Task findOneTask(@WebParam(name = "session") final Session session, @WebParam(name = "taskUuid") final String taskUuid) throws WrongSessionException {
        sessionService.existSession(session);
        return taskService.findOne(session.getUserId(), taskUuid);
    }

    @WebMethod
    public List<Task> findAllTasks(@WebParam(name = "session") final Session session) throws WrongSessionException {
        sessionService.existSession(session);
        return taskService.findAllByUserId(session.getUserId());
    }

    @WebMethod
    public List<Task> findAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) throws WrongSessionException {
        sessionService.existSession(session);
        return taskService.findAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) throws WrongSessionException {
        sessionService.existSession(session);
        taskService.removeAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public List<Task> findAllTasksSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) throws WrongSessionException {
        sessionService.existSession(session);
        return taskService.findAllSortedByValue(session.getUserId(), value);
    }
}
