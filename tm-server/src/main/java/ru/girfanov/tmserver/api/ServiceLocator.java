package ru.girfanov.tmserver.api;

import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmserver.api.service.IProjectService;
import ru.girfanov.tmserver.api.service.ITaskService;
import ru.girfanov.tmserver.api.service.IUserService;
import ru.girfanov.tmserver.entity.User;

public interface ServiceLocator {
    void setUser(User user);
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    IDataDomainService getDataDomainService();
    void init();
}
