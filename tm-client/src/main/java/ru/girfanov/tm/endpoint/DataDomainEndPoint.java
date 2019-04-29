package ru.girfanov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-29T17:11:05.522+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.girfanov.ru/", name = "DataDomainEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface DataDomainEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByFasterInXmlRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByFasterInXmlResponse")
    @RequestWrapper(localName = "saveDataByFasterInXml", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByFasterInXml")
    @ResponseWrapper(localName = "saveDataByFasterInXmlResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByFasterInXmlResponse")
    public void saveDataByFasterInXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByJaxbInJsonRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByJaxbInJsonResponse")
    @RequestWrapper(localName = "getDataByJaxbInJson", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByJaxbInJson")
    @ResponseWrapper(localName = "getDataByJaxbInJsonResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByJaxbInJsonResponse")
    public void getDataByJaxbInJson(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataBySerializationRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataBySerializationResponse")
    @RequestWrapper(localName = "saveDataBySerialization", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataBySerialization")
    @ResponseWrapper(localName = "saveDataBySerializationResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataBySerializationResponse")
    public void saveDataBySerialization(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByJaxbInXmlRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByJaxbInXmlResponse")
    @RequestWrapper(localName = "getDataByJaxbInXml", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByJaxbInXml")
    @ResponseWrapper(localName = "getDataByJaxbInXmlResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByJaxbInXmlResponse")
    public void getDataByJaxbInXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByFasterInJsonRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByFasterInJsonResponse")
    @RequestWrapper(localName = "saveDataByFasterInJson", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByFasterInJson")
    @ResponseWrapper(localName = "saveDataByFasterInJsonResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByFasterInJsonResponse")
    public void saveDataByFasterInJson(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByJaxbInXmlRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByJaxbInXmlResponse")
    @RequestWrapper(localName = "saveDataByJaxbInXml", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByJaxbInXml")
    @ResponseWrapper(localName = "saveDataByJaxbInXmlResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByJaxbInXmlResponse")
    public void saveDataByJaxbInXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByFasterInXmlRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByFasterInXmlResponse")
    @RequestWrapper(localName = "getDataByFasterInXml", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByFasterInXml")
    @ResponseWrapper(localName = "getDataByFasterInXmlResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByFasterInXmlResponse")
    public void getDataByFasterInXml(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByJaxbInJsonRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/saveDataByJaxbInJsonResponse")
    @RequestWrapper(localName = "saveDataByJaxbInJson", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByJaxbInJson")
    @ResponseWrapper(localName = "saveDataByJaxbInJsonResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.SaveDataByJaxbInJsonResponse")
    public void saveDataByJaxbInJson(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataBySerializationRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataBySerializationResponse")
    @RequestWrapper(localName = "getDataBySerialization", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataBySerialization")
    @ResponseWrapper(localName = "getDataBySerializationResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataBySerializationResponse")
    public void getDataBySerialization(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByFasterInJsonRequest", output = "http://endpoint.tm.girfanov.ru/DataDomainEndPoint/getDataByFasterInJsonResponse")
    @RequestWrapper(localName = "getDataByFasterInJson", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByFasterInJson")
    @ResponseWrapper(localName = "getDataByFasterInJsonResponse", targetNamespace = "http://endpoint.tm.girfanov.ru/", className = "ru.girfanov.tm.endpoint.GetDataByFasterInJsonResponse")
    public void getDataByFasterInJson(
        @WebParam(name = "session", targetNamespace = "")
        ru.girfanov.tm.endpoint.SessionDto session
    );
}
