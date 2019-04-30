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
 * 2019-04-30T12:25:32.273+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.girfanov.ru/", name = "ProjectEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/mergeProjectRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/mergeProjectResponse")
    @RequestWrapper(localName = "mergeProject", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeProject")
    @ResponseWrapper(localName = "mergeProjectResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeProjectResponse")
    public void mergeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "project", targetNamespace = "")
        ru.girfanov.tm.endpoint.ProjectDto project
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findAllProjectsSortedByValueRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findAllProjectsSortedByValueResponse")
    @RequestWrapper(localName = "findAllProjectsSortedByValue", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllProjectsSortedByValue")
    @ResponseWrapper(localName = "findAllProjectsSortedByValueResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllProjectsSortedByValueResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.ProjectDto> findAllProjectsSortedByValue(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "value", targetNamespace = "")
        java.lang.String value
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/removeProjectRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/removeProjectResponse")
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveProjectResponse")
    public void removeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "project", targetNamespace = "")
        ru.girfanov.tm.endpoint.ProjectDto project
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findAllProjectsRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findAllProjectsResponse")
    @RequestWrapper(localName = "findAllProjects", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllProjects")
    @ResponseWrapper(localName = "findAllProjectsResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllProjectsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.ProjectDto> findAllProjects(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/persistProjectRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/persistProjectResponse")
    @RequestWrapper(localName = "persistProject", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistProject")
    @ResponseWrapper(localName = "persistProjectResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistProjectResponse")
    public void persistProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "project", targetNamespace = "")
        ru.girfanov.tm.endpoint.ProjectDto project
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/removeAllProjectsRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/removeAllProjectsResponse")
    @RequestWrapper(localName = "removeAllProjects", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllProjects")
    @ResponseWrapper(localName = "removeAllProjectsResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveAllProjectsResponse")
    public void removeAllProjects(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findOneProjectRequest", output = "http://endpoint.tm.girfanov.ru/ProjectEndPoint/findOneProjectResponse")
    @RequestWrapper(localName = "findOneProject", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneProject")
    @ResponseWrapper(localName = "findOneProjectResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tm.endpoint.ProjectDto findOneProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "projectUuid", targetNamespace = "")
        java.lang.String projectUuid
    );
}
