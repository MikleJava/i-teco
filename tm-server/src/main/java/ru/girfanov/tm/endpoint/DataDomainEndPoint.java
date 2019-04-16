package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class DataDomainEndPoint {

    @NonNull private IDataDomainService dataDomainService;
    @NonNull private ISessionService sessionService;

    @WebMethod
    public void saveDataBySerialization(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session) ;
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.saveDataBySerialization();
    }

    @WebMethod
    public void getDataBySerialization(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.getDataBySerialization();
    }

    @WebMethod
    public void saveDataByJaxbInXml(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.saveDataByJaxbInXml();
    }

    @WebMethod
    public void getDataByJaxbInXml(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.getDataByJaxbInXml();
    }

    @WebMethod
    public void saveDataByJaxbInJson(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.saveDataByJaxbInJson();
    }

    @WebMethod
    public void getDataByJaxbInJson(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.getDataByJaxbInJson();
    }

    @WebMethod
    public void saveDataByFasterInXml(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.saveDataByFasterInXml();
    }

    @WebMethod
    public void getDataByFasterInXml(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.getDataByFasterInXml();
    }

    @WebMethod
    public void saveDataByFasterInJson(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.saveDataByFasterInJson();
    }

    @WebMethod
    public void getDataByFasterInJson(@WebParam(name = "session") final Session session) {
        try {
            sessionService.existSession(session);
        } catch (WrongSessionException e) {
            System.out.println(e.getMessage());
        }
        dataDomainService.getDataByFasterInJson();
    }
}
