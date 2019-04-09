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
 * 2019-04-09T12:21:28.202+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.girfanov.ru/", name = "SessionEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/SessionEndPoint/removeSessionRequest", output = "http://endpoint.tm.girfanov.ru/SessionEndPoint/removeSessionResponse")
    @RequestWrapper(localName = "removeSession", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveSession")
    @ResponseWrapper(localName = "removeSessionResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.RemoveSessionResponse")
    public void removeSession(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.Session session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/SessionEndPoint/createSessionRequest", output = "http://endpoint.tm.girfanov.ru/SessionEndPoint/createSessionResponse")
    @RequestWrapper(localName = "createSession", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.CreateSession")
    @ResponseWrapper(localName = "createSessionResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.CreateSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.girfanov.tm.endpoint.Session createSession(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );
}
