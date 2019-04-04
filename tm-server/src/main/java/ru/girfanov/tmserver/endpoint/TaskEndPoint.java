package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.ITaskService;
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

    @WebMethod
    public void persist(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final Task entity) {
        taskService.persist(userId, entity);
    }

    @WebMethod
    public void merge(@WebParam(name = "userId") final String userId, @WebParam(name = "entity") final Task entity) {
        taskService.merge(userId, entity);
    }

    @WebMethod
    public void remove(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        taskService.remove(userId, uuid);
    }

    @WebMethod
    public void removeAll(@WebParam(name = "userId") final String userId) {
        taskService.removeAll(userId);
    }

    @WebMethod
    public Task findOne(@WebParam(name = "userId") final String userId, @WebParam(name = "uuid") final String uuid) {
        return taskService.findOne(userId, uuid);
    }

    @WebMethod
    public List<Task> findAll(@WebParam(name = "userId") final String userId) {
        return taskService.findAll(userId);
    }

    @WebMethod
    public List<Task> findAllTasksByProjectId(@WebParam(name = "userId") final String userId, @WebParam(name = "projectId") final String projectId) {
        return taskService.findAllTasksByProjectId(userId, projectId);
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "userId") final String userId, @WebParam(name = "projectId") final String projectId) {
        taskService.removeAllTasksByProjectId(userId, projectId);
    }

    @WebMethod
    public List<Task> findAllSortedByValue(@WebParam(name = "userId") final String userId, @WebParam(name = "value") final String value) {
        return taskService.findAllSortedByValue(userId, value);
    }
}
