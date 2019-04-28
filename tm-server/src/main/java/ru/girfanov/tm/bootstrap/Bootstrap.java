package ru.girfanov.tm.bootstrap;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.api.ServiceLocator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@ApplicationScoped
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {

    @NotNull private static final String PROJECT_ENDPOINT = "http://localhost:8080/ProjectEndpoint?wsdl";
    @NotNull private static final String TASK_ENDPOINT = "http://localhost:8080/TaskEndpoint?wsdl";
    @NotNull private static final String USER_ENDPOINT = "http://localhost:8080/UserEndpoint?wsdl";
    @NotNull private static final String DATA_DOMAIN_ENDPOINT = "http://localhost:8080/DataDomainEndpoint?wsdl";
    @NotNull private static final String SESSION_ENDPOINT = "http://localhost:8080/SessionEndPoint?wsdl";

    @Inject private ProjectEndPoint projectEndPoint;
    @Inject private TaskEndPoint taskEndPoint;
    @Inject private UserEndPoint userEndPoint;
    @Inject private DataDomainEndPoint dataDomainEndPoint;
    @Inject private SessionEndPoint sessionEndPoint;

    @Override
    public void init() {
        Endpoint.publish(PROJECT_ENDPOINT, projectEndPoint);
        System.out.println(PROJECT_ENDPOINT + " has been started");
        Endpoint.publish(TASK_ENDPOINT, taskEndPoint);
        System.out.println(TASK_ENDPOINT + " has been started");
        Endpoint.publish(USER_ENDPOINT, userEndPoint);
        System.out.println(USER_ENDPOINT + " has been started");
        Endpoint.publish(DATA_DOMAIN_ENDPOINT, dataDomainEndPoint);
        System.out.println(DATA_DOMAIN_ENDPOINT + " has been started");
        Endpoint.publish(SESSION_ENDPOINT, sessionEndPoint);
        System.out.println(SESSION_ENDPOINT + " has been started");
    }
}
