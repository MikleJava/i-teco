package ru.girfanov.tm.api;

import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;

public interface ServiceLocator {
    void setUser(User user);
    IProjectService getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    void init();
}
