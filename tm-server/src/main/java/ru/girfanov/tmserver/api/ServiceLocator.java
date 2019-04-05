package ru.girfanov.tmserver.api;

import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmserver.api.service.IProjectService;
import ru.girfanov.tmserver.api.service.ITaskService;
import ru.girfanov.tmserver.api.service.IUserService;

public interface ServiceLocator {
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    IDataDomainService getDataDomainService();
    void init();
}
