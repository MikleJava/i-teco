package ru.girfanov.tm.util;

import lombok.NoArgsConstructor;
import ru.girfanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
@NoArgsConstructor
public class EndPointProducerUtil {

    @Inject private ProjectEndPointService projectEndPointService;
    @Inject private TaskEndPointService taskEndPointService;
    @Inject private UserEndPointService userEndPointService;
    @Inject private SessionEndPointService sessionEndPointService;
    @Inject private DataDomainEndPointService dataDomainEndPointService;

    @Produces
    public ProjectEndPoint getProjectEndPoint() {
        return projectEndPointService.getProjectEndPointPort();
    }

    @Produces
    public TaskEndPoint getTaskEndPoint() {
        return taskEndPointService.getTaskEndPointPort();
    }

    @Produces
    public UserEndPoint getUserEndPoint() {
        return userEndPointService.getUserEndPointPort();
    }

    @Produces
    public SessionEndPoint getSessionEndPoint() {
        return sessionEndPointService.getSessionEndPointPort();
    }

    @Produces
    public DataDomainEndPoint getDataDomainEndPoint() {
        return dataDomainEndPointService.getDataDomainEndPointPort();
    }

}
