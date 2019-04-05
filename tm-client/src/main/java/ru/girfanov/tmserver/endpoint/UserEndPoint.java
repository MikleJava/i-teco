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
 * 2019-04-05T17:03:41.419+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tmserver.girfanov.ru/", name = "UserEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/removeAllUsersRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/removeAllUsersResponse")
    @RequestWrapper(localName = "removeAllUsers", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveAllUsers")
    @ResponseWrapper(localName = "removeAllUsersResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveAllUsersResponse")
    public void removeAllUsers(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findOneUserByLoginAndPasswordRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findOneUserByLoginAndPasswordResponse")
    @RequestWrapper(localName = "findOneUserByLoginAndPassword", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneUserByLoginAndPassword")
    @ResponseWrapper(localName = "findOneUserByLoginAndPasswordResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneUserByLoginAndPasswordResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tmserver.endpoint.User findOneUserByLoginAndPassword(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/persistUserRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/persistUserResponse")
    @RequestWrapper(localName = "persistUser", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.PersistUser")
    @ResponseWrapper(localName = "persistUserResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.PersistUserResponse")
    public void persistUser(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "user", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.User user
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/removeUserRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/removeUserResponse")
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.RemoveUserResponse")
    public void removeUser(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "userUuid", targetNamespace = "")
        java.lang.String userUuid
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findAllUsersRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findAllUsersResponse")
    @RequestWrapper(localName = "findAllUsers", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllUsers")
    @ResponseWrapper(localName = "findAllUsersResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindAllUsersResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.girfanov.tmserver.endpoint.User> findAllUsers(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/mergeUserRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/mergeUserResponse")
    @RequestWrapper(localName = "mergeUser", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeUser")
    @ResponseWrapper(localName = "mergeUserResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeUserResponse")
    public void mergeUser(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "user", targetNamespace = "")
        ru.girfanov.tmserver.endpoint.User user
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findOneUserRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/findOneUserResponse")
    @RequestWrapper(localName = "findOneUser", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneUser")
    @ResponseWrapper(localName = "findOneUserResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.FindOneUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tmserver.endpoint.User findOneUser(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "userUuid", targetNamespace = "")
        java.lang.String userUuid
    );

    @WebMethod
    @Action(input = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/mergeUserPasswordRequest", output = "http://endpoint.tmserver.girfanov.ru/UserEndPoint/mergeUserPasswordResponse")
    @RequestWrapper(localName = "mergeUserPassword", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeUserPassword")
    @ResponseWrapper(localName = "mergeUserPasswordResponse", targetNamespace = "http://endpoint.tmserver.girfanov.ru/", className = "ru.girfanov.tmserver.endpoint.MergeUserPasswordResponse")
    public void mergeUserPassword(
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "newPassword", targetNamespace = "")
        java.lang.String newPassword
    );
}
