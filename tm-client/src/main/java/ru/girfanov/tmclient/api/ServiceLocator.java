package ru.girfanov.tmclient.api;

public interface ServiceLocator {
    void setUser(User user);
    ProjectE getProjectService();
    ITaskService getTaskService();
    IUserService getUserService();
    IDataDomainService getDataDomainService();
    void init(Class[] commandClasses);
    void registerCommand(Class clazz);
}
