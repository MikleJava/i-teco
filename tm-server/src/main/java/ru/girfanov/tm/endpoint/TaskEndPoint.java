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
    public void persistTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        taskService.persist(session.getUserId(), task);
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        taskService.merge(session.getUserId(), task);
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        taskService.remove(session.getUserId(), task);
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        taskService.removeAllByUserId(session.getUserId());
    }

    @WebMethod
    public Task findOneTask(@WebParam(name = "session") final Session session, @WebParam(name = "taskUuid") final String taskUuid) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return taskService.findOne(session.getUserId(), taskUuid);
    }

    @WebMethod
    public List<Task> findAllTasks(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return taskService.findAllByUserId(session.getUserId());
    }

    @WebMethod
    public List<Task> findAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return taskService.findAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        taskService.removeAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public List<Task> findAllTasksSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        return taskService.findAllSortedByValue(session.getUserId(), value);
    }
}
