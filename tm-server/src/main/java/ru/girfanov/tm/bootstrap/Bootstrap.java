package ru.girfanov.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.service.PropertyService;

import javax.xml.ws.Endpoint;

@Component
public class Bootstrap implements ServiceLocator {

    @Nullable private final static String PORT = PropertyService.setApplicationPort("app.port", System.getProperty("server.port", "8080"));

    @NotNull private static final String PROJECT_ENDPOINT = "http://localhost:" + PORT + "/ProjectEndpoint?wsdl";
    @NotNull private static final String TASK_ENDPOINT = "http://localhost:" + PORT + "/TaskEndpoint?wsdl";
    @NotNull private static final String USER_ENDPOINT = "http://localhost:" + PORT + "/UserEndpoint?wsdl";
    @NotNull private static final String DATA_DOMAIN_ENDPOINT = "http://localhost:" + PORT + "/DataDomainEndpoint?wsdl";
    @NotNull private static final String SESSION_ENDPOINT = "http://localhost:" + PORT + "/SessionEndPoint?wsdl";

    @Autowired
    private ProjectEndPoint projectEndPoint;
    @Autowired
    private TaskEndPoint taskEndPoint;
    @Autowired
    private UserEndPoint userEndPoint;
    @Autowired
    private DataDomainEndPoint dataDomainEndPoint;
    @Autowired
    private SessionEndPoint sessionEndPoint;

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
