package ru.girfanov.tmserver.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.api.ServiceLocator;
import ru.girfanov.tmserver.api.repository.IProjectRepository;
import ru.girfanov.tmserver.api.repository.ISessionRepository;
import ru.girfanov.tmserver.api.repository.ITaskRepository;
import ru.girfanov.tmserver.api.repository.IUserRepository;
import ru.girfanov.tmserver.api.service.*;
import ru.girfanov.tmserver.endpoint.*;
import ru.girfanov.tmserver.repository.ProjectRepository;
import ru.girfanov.tmserver.repository.SessionRepository;
import ru.girfanov.tmserver.repository.TaskRepository;
import ru.girfanov.tmserver.repository.UserRepository;
import ru.girfanov.tmserver.service.*;

import javax.xml.ws.Endpoint;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NotNull private static final String PROJECT_ENDPOINT = "http://localhost:8080/ProjectEndpoint?wsdl";
    @NotNull private static final String TASK_ENDPOINT = "http://localhost:8080/TaskEndpoint?wsdl";
    @NotNull private static final String USER_ENDPOINT = "http://localhost:8080/UserEndpoint?wsdl";
    @NotNull private static final String DATA_DOMAIN_ENDPOINT = "http://localhost:8080/DataDomainEndpoint?wsdl";
    @NotNull private static final String SESSION_ENDPOINT = "http://localhost:8080/SessionEndPoint?wsdl";

    @NotNull private final IProjectRepository projectRepository = new ProjectRepository();
    @NotNull private final ITaskRepository taskRepository = new TaskRepository();
    @NotNull private final IUserRepository userRepository = new UserRepository();
    @NotNull private final ISessionRepository sessionRepository = new SessionRepository();

    @NotNull @Getter private final IProjectService projectService = new ProjectService(userRepository, projectRepository, taskRepository);
    @NotNull @Getter private final ITaskService taskService = new TaskService(taskRepository);
    @NotNull @Getter private final IUserService userService = new UserService(userRepository);
    @NotNull @Getter private final IDataDomainService dataDomainService = new DataDomainService(projectRepository, taskRepository, userRepository);
    @NotNull @Getter private final ISessionService sessionService = new SessionService(sessionRepository, userRepository);

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