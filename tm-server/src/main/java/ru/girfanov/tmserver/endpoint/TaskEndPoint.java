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
    public void persistTask(@WebParam(name = "userId") final String userId, @WebParam(name = "task") final Task task) {
        taskService.persist(userId, task);
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "userId") final String userId, @WebParam(name = "task") final Task task) {
        taskService.merge(userId, task);
    }

    @WebMethod
    public void removeTask(@WebParam(name = "userId") final String userId, @WebParam(name = "taskUuid") final String taskUuid) {
        taskService.remove(userId, taskUuid);
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "userId") final String userId) {
        taskService.removeAll(userId);
    }

    @WebMethod
    public Task findOneTask(@WebParam(name = "userId") final String userId, @WebParam(name = "taskUuid") final String taskUuid) {
        return taskService.findOne(userId, taskUuid);
    }

    @WebMethod
    public List<Task> findAllTasks(@WebParam(name = "userId") final String userId) {
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
    public List<Task> findAllTasksSortedByValue(@WebParam(name = "userId") final String userId, @WebParam(name = "value") final String value) {
        return taskService.findAllSortedByValue(userId, value);
    }
}
