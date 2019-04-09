package ru.girfanov.tm.api.service;

public interface IDataDomainService {
    void saveDataBySerialization();
    void getDataBySerialization();
    void saveDataByJaxbInXml();
    void getDataByJaxbInXml();
    void saveDataByJaxbInJson();
    void getDataByJaxbInJson();
    void saveDataByFasterInXml();
    void getDataByFasterInXml();
    void saveDataByFasterInJson();
    void getDataByFasterInJson();
}
