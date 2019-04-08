package ru.girfanov.tmserver.endpoint;

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
 * 2019-04-08T12:31:21.833+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.girfanov.ru/", name = "ProjectEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findAllProjectsSortedByValueRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findAllProjectsSortedByValueResponse")
    @RequestWrapper(localName = "findAllProjectsSortedByValue", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllProjectsSortedByValue")
    @ResponseWrapper(localName = "findAllProjectsSortedByValueResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllProjectsSortedByValueResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tmserver.endpoint.Project> findAllProjectsSortedByValue(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session,
        @WebParam(name = "value", targetNamespace = "")
        java.lang.String value
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findAllProjectsRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findAllProjectsResponse")
    @RequestWrapper(localName = "findAllProjects", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllProjects")
    @ResponseWrapper(localName = "findAllProjectsResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllProjectsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tmserver.endpoint.Project> findAllProjects(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/removeProjectRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/removeProjectResponse")
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveProjectResponse")
    public void removeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session,
        @WebParam(name = "projectUuid", targetNamespace = "")
        java.lang.String projectUuid
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findOneProjectRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/findOneProjectResponse")
    @RequestWrapper(localName = "findOneProject", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneProject")
    @ResponseWrapper(localName = "findOneProjectResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tmserver.endpoint.Project findOneProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session,
        @WebParam(name = "projectUuid", targetNamespace = "")
        java.lang.String projectUuid
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/persistProjectRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/persistProjectResponse")
    @RequestWrapper(localName = "persistProject", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.PersistProject")
    @ResponseWrapper(localName = "persistProjectResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.PersistProjectResponse")
    public void persistProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session,
        @WebParam(name = "project", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Project project
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/removeAllProjectsRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/removeAllProjectsResponse")
    @RequestWrapper(localName = "removeAllProjects", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveAllProjects")
    @ResponseWrapper(localName = "removeAllProjectsResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveAllProjectsResponse")
    public void removeAllProjects(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/mergeProjectRequest", output = "http://endpoint.tmserver.girfanov.ru/ProjectEndPoint/mergeProjectResponse")
    @RequestWrapper(localName = "mergeProject", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeProject")
    @ResponseWrapper(localName = "mergeProjectResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeProjectResponse")
    public void mergeProject(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Session session,
        @WebParam(name = "project", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.Project project
    );
}
