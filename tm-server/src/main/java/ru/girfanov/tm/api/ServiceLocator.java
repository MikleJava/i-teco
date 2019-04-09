package ru.girfanov.tm.api;

import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;

public interface ServiceLocator {
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    IDataDomainService getDataDomainService();
    void init();
}
