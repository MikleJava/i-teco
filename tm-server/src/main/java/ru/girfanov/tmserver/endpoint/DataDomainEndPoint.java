package ru.girfanov.tmserver.endpoint;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.girfanov.tmserver.api.service.IDataDomainService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@RequiredArgsConstructor
public class DataDomainEndPoint {

    @NonNull private IDataDomainService dataDomainService;

    @WebMethod
    public void saveDataBySerialization() {
        dataDomainService.saveDataBySerialization();
    }

    @WebMethod
    public void getDataBySerialization() {
        dataDomainService.getDataBySerialization();
    }

    @WebMethod
    public void saveDataByJaxbInXml() {
        dataDomainService.saveDataByJaxbInXml();
    }

    @WebMethod
    public void getDataByJaxbInXml() {
        dataDomainService.getDataByJaxbInXml();
    }

    @WebMethod
    public void saveDataByJaxbInJson() {
        dataDomainService.saveDataByJaxbInJson();
    }

    @WebMethod
    public void getDataByJaxbInJson() {
        dataDomainService.getDataByJaxbInJson();
    }

    @WebMethod
    public void saveDataByFasterInXml() {
        dataDomainService.saveDataByFasterInXml();
    }

    @WebMethod
    public void getDataByFasterInXml() {
        dataDomainService.getDataByFasterInXml();
    }

    @WebMethod
    public void saveDataByFasterInJson() {
        dataDomainService.saveDataByFasterInJson();
    }

    @WebMethod
    public void getDataByFasterInJson() {
        dataDomainService.getDataByFasterInJson();
    }
}
