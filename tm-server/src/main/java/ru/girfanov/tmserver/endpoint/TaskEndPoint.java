package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.api.service.ITaskService;
import ru.girfanov.tmserver.entity.Session;
import ru.girfanov.tmserver.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskEndPoint {

    @NonNull private ITaskService taskService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void persistTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) taskService.persist(session.getUserId(), task);
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "session") final Session session, @WebParam(name = "task") final Task task) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) taskService.merge(session.getUserId(), task);
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final Session session, @WebParam(name = "taskUuid") final String taskUuid) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) taskService.remove(session.getUserId(), taskUuid);
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) taskService.removeAll(session.getUserId());
    }

    @WebMethod
    public Task findOneTask(@WebParam(name = "session") final Session session, @WebParam(name = "taskUuid") final String taskUuid) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return taskService.findOne(session.getUserId(), taskUuid);
    }

    @WebMethod
    public List<Task> findAllTasks(@WebParam(name = "session") final Session session) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return taskService.findAll(session.getUserId());
    }

    @WebMethod
    public List<Task> findAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return taskService.findAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "session") final Session session, @WebParam(name = "projectId") final String projectId) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) taskService.removeAllTasksByProjectId(session.getUserId(), projectId);
    }

    @WebMethod
    public List<Task> findAllTasksSortedByValue(@WebParam(name = "session") final Session session, @WebParam(name = "value") final String value) {
        if(!sessionService.existSession(session.getUserId(), session.getUuid())) return null;
        return taskService.findAllSortedByValue(session.getUserId(), value);
    }
}
