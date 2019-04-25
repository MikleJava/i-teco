package ru.girfanov.tm.api;

import ru.girfanov.tm.endpoint.*;

public interface ServiceLocator {
    ProjectEndPoint getProjectEndPoint();
    TaskEndPoint getTaskEndPoint();
    UserEndPoint getUserEndPoint();
    DataDomainEndPoint getDataDomainEndPoint();
    SessionEndPoint getSessionEndPoint();
    void init(Class[] commandClasses);
    void registerCommand(Class clazz);
    void setSessionDto(SessionDto sessionDto);
}
