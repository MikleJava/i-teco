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
 * 2019-05-07T16:05:20.064+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.girfanov.ru/", name = "UserEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/findAllUsersRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/findAllUsersResponse")
    @RequestWrapper(localName = "findAllUsers", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllUsers")
    @ResponseWrapper(localName = "findAllUsersResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllUsersResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.UserDto> findAllUsers(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/mergeUserRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/mergeUserResponse")
    @RequestWrapper(localName = "mergeUser", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeUser")
    @ResponseWrapper(localName = "mergeUserResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.MergeUserResponse")
    public void mergeUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "user", targetNamespace = "")
        ru.girfanov.tm.endpoint.UserDto user
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/findAllRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/findAllResponse")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tm.endpoint.UserDto> findAll();

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/findOneUserRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/findOneUserResponse")
    @RequestWrapper(localName = "findOneUser", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneUser")
    @ResponseWrapper(localName = "findOneUserResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tm.endpoint.UserDto findOneUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "userUuid", targetNamespace = "")
        java.lang.String userUuid
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/persistUserRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/persistUserResponse")
    @RequestWrapper(localName = "persistUser", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistUser")
    @ResponseWrapper(localName = "persistUserResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.PersistUserResponse")
    public void persistUser(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "role", targetNamespace = "")
        ru.girfanov.tm.endpoint.Role role
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/removeUserRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/removeUserResponse")
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveUserResponse")
    public void removeUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session,
        @WebParam(name = "user", targetNamespace = "")
        ru.girfanov.tm.endpoint.UserDto user
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/UserEndPoint/findOneUserByLoginRequest", output = "http://endpoint.tm.girfanov.ru/UserEndPoint/findOneUserByLoginResponse")
    @RequestWrapper(localName = "findOneUserByLogin", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneUserByLogin")
    @ResponseWrapper(localName = "findOneUserByLoginResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.FindOneUserByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tm.endpoint.UserDto findOneUserByLogin(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    );
}
