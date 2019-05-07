package ru.girfanov.tm.command.crud;

import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.girfanov.tm.endpoint.*;
import ru.girfanov.tm.util.EndPointProducerUtil;

import javax.xml.datatype.DatatypeConfigurationException;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static ru.girfanov.tm.util.DateConverterGregorianCalendar.convert;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EndPointProducerUtil.class)
public class CrudCommandTest {

    @Autowired
    private ProjectEndPoint projectEndPoint;
    @Autowired
    private TaskEndPoint taskEndPoint;
    @Autowired
    private UserEndPoint userEndPoint;
    @Autowired
    private SessionEndPoint sessionEndPoint;

    private SessionDto session;
    private UserDto user;

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";

    @NotNull private static final String projectId = UUID.randomUUID().toString();
    @NotNull private static final String userId = UUID.randomUUID().toString();
    @NotNull private static final String taskId = UUID.randomUUID().toString();

    @Before
    public void setUp() {
        user = new UserDto();
        user.setId(userId);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setRole(Role.USER);
        userEndPoint.persistUser(userId, LOGIN, PASSWORD, Role.USER);
        session = sessionEndPoint.createSession(LOGIN);
        user = userEndPoint.findOneUserByLogin(LOGIN);
    }

    @After
    public void setDown() {
        userEndPoint.removeUser(session, user);
    }

    @Test
    public void selectUser() {
        assertNotNull(userEndPoint.findOneUserByLogin(LOGIN));
    }

    @Test
    public void existSession() {
        assertNotNull(session);
    }

    @Test
    public void updateUserPassword() {
        final String newPassword = "newPassword";
        user.setPassword(newPassword);
        userEndPoint.mergeUser(session, user);
        final UserDto updatedUser = userEndPoint.findOneUserByLogin(LOGIN);
        assertNotNull(updatedUser.getPassword());
    }

    @Test
    public void createProject() throws DatatypeConfigurationException {
        final ProjectDto project = new ProjectDto();
        project.setId(projectId);
        project.setName("proj");
        project.setDescription("desc");
        project.setStatus(Status.PROCESS);
        project.setUserId(userId);
        project.setDateStart(convert(new Date()));
        project.setDateEnd(convert(new Date()));
        projectEndPoint.persistProject(session, project);
        assertNotNull(projectEndPoint.findOneProject(session, projectId));
    }

    @Test
    public void updateProject() {
        final ProjectDto project = projectEndPoint.findOneProject(session, projectId);
        project.setDescription("newDesc");
        projectEndPoint.mergeProject(session, project);
        assertEquals(projectEndPoint.findOneProject(session, projectId).getDescription(), "newDesc");
    }

    @Test
    public void createTask() throws DatatypeConfigurationException {
        final TaskDto task = new TaskDto();
        task.setId(taskId);
        task.setName("task");
        task.setDescription("desc");
        task.setUserId(userId);
        task.setStatus(Status.PLANNING);
        task.setDateStart(convert(new Date()));
        task.setDateEnd(convert(new Date()));
        task.setProjectId(projectId);
        taskEndPoint.persistTask(session, task);
        assertNotNull(taskEndPoint.findOneTask(session, taskId));
    }

    @Test
    public void updateTask() {
        final TaskDto task = taskEndPoint.findOneTask(session, taskId);
        task.setDescription("newDesc");
        taskEndPoint.mergeTask(session, task);
        assertEquals(taskEndPoint.findOneTask(session, taskId).getDescription(), "newDesc");
    }

    @Test
    public void deleteProjectCascadeTask() {
        final ProjectDto project = projectEndPoint.findOneProject(session, projectId);
        projectEndPoint.removeProject(session, project);
        assertNull(projectEndPoint.findOneProject(session, projectId));
        assertNull(taskEndPoint.findOneTask(session, taskId));
    }
}