package ru.girfanov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.service.*;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.service.*;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.util.HibernateConnectorUtil;

import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NotNull private static final String PROJECT_ENDPOINT = "http://localhost:8080/ProjectEndpoint?wsdl";
    @NotNull private static final String TASK_ENDPOINT = "http://localhost:8080/TaskEndpoint?wsdl";
    @NotNull private static final String USER_ENDPOINT = "http://localhost:8080/UserEndpoint?wsdl";
    @NotNull private static final String DATA_DOMAIN_ENDPOINT = "http://localhost:8080/DataDomainEndpoint?wsdl";
    @NotNull private static final String SESSION_ENDPOINT = "http://localhost:8080/SessionEndPoint?wsdl";

    @NotNull final private EntityManagerFactory entityManagerFactory = HibernateConnectorUtil.factory();

    @NotNull @Getter private final IProjectService projectService = new ProjectService(entityManagerFactory);
    @NotNull @Getter private final ITaskService taskService = new TaskService(entityManagerFactory);
    @NotNull @Getter private final IUserService userService = new UserService(entityManagerFactory);
    @NotNull @Getter private final ISessionService sessionService = new SessionService(entityManagerFactory);
    @NotNull @Getter private final IDataDomainService dataDomainService = new DataDomainService();

    @Override
    public void init() {
        Endpoint.publish(PROJECT_ENDPOINT, new ProjectEndPoint(projectService, sessionService));
        System.out.println(PROJECT_ENDPOINT + " has been started");
        Endpoint.publish(TASK_ENDPOINT, new TaskEndPoint(taskService, sessionService));
        System.out.println(TASK_ENDPOINT + " has been started");
        Endpoint.publish(USER_ENDPOINT, new UserEndPoint(userService, sessionService));
        System.out.println(USER_ENDPOINT + " has been started");
        Endpoint.publish(DATA_DOMAIN_ENDPOINT, new DataDomainEndPoint(dataDomainService, sessionService));
        System.out.println(DATA_DOMAIN_ENDPOINT + " has been started");
        Endpoint.publish(SESSION_ENDPOINT, new SessionEndPoint(sessionService));
        System.out.println(SESSION_ENDPOINT + " has been started");
    }
}
