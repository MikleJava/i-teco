package ru.girfanov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-28T11:42:10.690+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.girfanov.ru/", name = "TaskEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface TaskEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeAllTasksByProjectIdRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeAllTasksByProjectIdResponse")
    @RequestWrapper(localName = "removeAllTasksByProjectId", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllTasksByProjectId")
    @ResponseWrapper(localName = "removeAllTasksByProjectIdResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllTasksByProjectIdResponse")
    public void removeAllTasksByProjectId(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksSortedByValueRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksSortedByValueResponse")
    @RequestWrapper(localName = "findAllTasksSortedByValue", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasksSortedByValue")
    @ResponseWrapper(localName = "findAllTasksSortedByValueResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasksSortedByValueResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.TaskDto> findAllTasksSortedByValue(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "value", targetNamespace = "")
        java.lang.String value
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksResponse")
    @RequestWrapper(localName = "findAllTasks", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasks")
    @ResponseWrapper(localName = "findAllTasksResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.TaskDto> findAllTasks(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeAllTasksRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeAllTasksResponse")
    @RequestWrapper(localName = "removeAllTasks", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllTasks")
    @ResponseWrapper(localName = "removeAllTasksResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllTasksResponse")
    public void removeAllTasks(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeTaskRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/removeTaskResponse")
    @RequestWrapper(localName = "removeTask", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveTask")
    @ResponseWrapper(localName = "removeTaskResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveTaskResponse")
    public void removeTask(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "task", targetNamespace = "")
        ru.girfanov.tm.endpoint.TaskDto task
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksByProjectIdRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findAllTasksByProjectIdResponse")
    @RequestWrapper(localName = "findAllTasksByProjectId", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasksByProjectId")
    @ResponseWrapper(localName = "findAllTasksByProjectIdResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllTasksByProjectIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.TaskDto> findAllTasksByProjectId(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "projectId", targetNamespace = "")
        java.lang.String projectId
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/mergeTaskRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/mergeTaskResponse")
    @RequestWrapper(localName = "mergeTask", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeTask")
    @ResponseWrapper(localName = "mergeTaskResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeTaskResponse")
    public void mergeTask(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "task", targetNamespace = "")
        ru.girfanov.tm.endpoint.TaskDto task
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/persistTaskRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/persistTaskResponse")
    @RequestWrapper(localName = "persistTask", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistTask")
    @ResponseWrapper(localName = "persistTaskResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistTaskResponse")
    public void persistTask(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "task", targetNamespace = "")
        ru.girfanov.tm.endpoint.TaskDto task
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findOneTaskRequest", output = "http://endpoint.tm.girfanov.ru/TaskEndPoint/findOneTaskResponse")
    @RequestWrapper(localName = "findOneTask", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneTask")
    @ResponseWrapper(localName = "findOneTaskResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tm.endpoint.TaskDto findOneTask(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "taskUuid", targetNamespace = "")
        java.lang.String taskUuid
    );
}
