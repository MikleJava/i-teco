package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.entity.Session;

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
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.saveDataBySerialization();
    }

    @WebMethod
    public void getDataBySerialization(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.getDataBySerialization();
    }

    @WebMethod
    public void saveDataByJaxbInXml(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.saveDataByJaxbInXml();
    }

    @WebMethod
    public void getDataByJaxbInXml(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.getDataByJaxbInXml();
    }

    @WebMethod
    public void saveDataByJaxbInJson(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.saveDataByJaxbInJson();
    }

    @WebMethod
    public void getDataByJaxbInJson(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.getDataByJaxbInJson();
    }

    @WebMethod
    public void saveDataByFasterInXml(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.saveDataByFasterInXml();
    }

    @WebMethod
    public void getDataByFasterInXml(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.getDataByFasterInXml();
    }

    @WebMethod
    public void saveDataByFasterInJson(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.saveDataByFasterInJson();
    }

    @WebMethod
    public void getDataByFasterInJson(@WebParam(name = "session") final Session session) {
        if(sessionService.existSession(session.getUserId(), session.getUuid())) dataDomainService.getDataByFasterInJson();
    }
}
