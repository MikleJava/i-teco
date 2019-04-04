package ru.girfanov.tmserver.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.api.ServiceLocator;
import ru.girfanov.tmserver.api.repository.IProjectRepository;
import ru.girfanov.tmserver.api.repository.ITaskRepository;
import ru.girfanov.tmserver.api.repository.IUserRepository;
import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmserver.api.service.IProjectService;
import ru.girfanov.tmserver.api.service.ITaskService;
import ru.girfanov.tmserver.api.service.IUserService;
import ru.girfanov.tmserver.endpoint.DataDomainEndPoint;
import ru.girfanov.tmserver.endpoint.ProjectEndPoint;
import ru.girfanov.tmserver.endpoint.TaskEndPoint;
import ru.girfanov.tmserver.endpoint.UserEndPoint;
import ru.girfanov.tmserver.entity.User;
import ru.girfanov.tmserver.repository.ProjectRepository;
import ru.girfanov.tmserver.repository.TaskRepository;
import ru.girfanov.tmserver.repository.UserRepository;
import ru.girfanov.tmserver.service.DataDomainService;
import ru.girfanov.tmserver.service.ProjectService;
import ru.girfanov.tmserver.service.TaskService;
import ru.girfanov.tmserver.service.UserService;

import javax.xml.ws.Endpoint;

@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @NotNull private static final String PROJECT_ENDPOINT = "http://localhost:8080/ProjectEndpoint?wsdl";
    @NotNull private static final String TASK_ENDPOINT = "http://localhost:8080/TaskEndpoint?wsdl";
    @NotNull private static final String USER_ENDPOINT = "http://localhost:8080/UserEndpoint?wsdl";
    @NotNull private static final String DATA_DOMAIN_ENDPOINT = "http://localhost:8080/DataDomainEndpoint?wsdl";

    @NotNull private final IProjectRepository projectRepository = new ProjectRepository();
    @NotNull private final ITaskRepository taskRepository = new TaskRepository();
    @NotNull private final IUserRepository userRepository = new UserRepository();

    @NotNull @Getter private final IProjectService projectService = new ProjectService(userRepository, projectRepository, taskRepository);
    @NotNull @Getter private final ITaskService taskService = new TaskService(taskRepository);
    @NotNull @Getter private final IUserService userService = new UserService(userRepository);
    @NotNull @Getter private final IDataDomainService dataDomainService = new DataDomainService(projectRepository, taskRepository, userRepository);

    @Nullable
    private User user;

    @Nullable
    private String command = null;

    @Override
    public void setUser(@Nullable final User user) {
        this.user = user;
    }

    @Override
    public void init() {
        Endpoint.publish(PROJECT_ENDPOINT, new ProjectEndPoint(projectService));
        Endpoint.publish(TASK_ENDPOINT, new TaskEndPoint(taskService));
        Endpoint.publish(USER_ENDPOINT, new UserEndPoint(userService));
        Endpoint.publish(DATA_DOMAIN_ENDPOINT, new DataDomainEndPoint(dataDomainService));
    }
}
