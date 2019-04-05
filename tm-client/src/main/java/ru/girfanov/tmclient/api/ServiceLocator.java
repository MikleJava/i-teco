package ru.girfanov.tmclient.api;

import ru.girfanov.tmserver.endpoint.*;

public interface ServiceLocator {
    ProjectEndPoint getProjectEndPoint();
    TaskEndPoint getTaskEndPoint();
    UserEndPoint getUserEndPoint();
    DataDomainEndPoint getDataDomainEndPoint();
    void init(Class[] commandClasses);
    void registerCommand(Class clazz);
    void setUser(User user);
}
