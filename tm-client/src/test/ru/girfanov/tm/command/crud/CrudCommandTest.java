//package ru.girfanov.tm.command.crud;
//
//import org.jetbrains.annotations.NotNull;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import ru.girfanov.tm.endpoint.*;
//
//import javax.enterprise.inject.se.SeContainer;
//import javax.enterprise.inject.se.SeContainerInitializer;
//import javax.xml.datatype.DatatypeConfigurationException;
//
//import java.util.Date;
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//import static ru.girfanov.tm.util.DateConverterGregorianCalendar.convert;
//
//public class CrudCommandTest {
//
//    private static SeContainer seContainer;
//    private static ProjectEndPoint projectEndPoint;
//    private static TaskEndPoint taskEndPoint;
//    private static UserEndPoint userEndPoint;
//    private static SessionEndPoint sessionEndPoint;
//
//    private static SessionDto session;
//    private static UserDto user;
//
//    @NotNull private static final String LOGIN = "test";
//    @NotNull private static final String PASSWORD = "test";
//
//    @NotNull private static final String projectId = UUID.randomUUID().toString();
//    @NotNull private static final String userId = UUID.randomUUID().toString();
//    @NotNull private static final String taskId = UUID.randomUUID().toString();
//
//    @BeforeClass
//    public static void setUp() {
//        seContainer = SeContainerInitializer.newInstance().addPackages(CrudCommandTest.class).initialize();
//        projectEndPoint = seContainer.select(ProjectEndPoint.class).get();
//        taskEndPoint = seContainer.select(TaskEndPoint.class).get();
//        userEndPoint = seContainer.select(UserEndPoint.class).get();
//        sessionEndPoint = seContainer.select(SessionEndPoint.class).get();
//
//        user = new UserDto();
//        user.setId(userId);
//        user.setLogin(LOGIN);
//        user.setPassword(PASSWORD);
//        user.setRole(Role.USER);
//        userEndPoint.persistUser(userId, LOGIN, PASSWORD, Role.USER);
//        session = sessionEndPoint.createSession(LOGIN);
//    }
//
//    @AfterClass
//    public static void setDown() {
//        if(projectEndPoint.findOneProject(session, projectId) != null) projectEndPoint.removeAllProjects(session);
//        if(userEndPoint.findOneUser(session, userId) != null) userEndPoint.removeUser(session, user);
//    }
//
//    @Test
//    public void selectUser() {
//        assertNotNull(userEndPoint.findOneUserByLogin(LOGIN));
//    }
//
//    @Test
//    public void existSession() {
//        assertNotNull(session);
//    }
//
//    @Test
//    public void updateUserPassword() {
//        final UserDto user = userEndPoint.findOneUserByLogin(LOGIN);
//        final String newPassword = "newPassword";
//        user.setPassword(newPassword);
//        userEndPoint.mergeUser(session, user);
//        final UserDto updatedUser = userEndPoint.findOneUserByLogin(LOGIN);
//        assertNotNull(updatedUser.getPassword());
//    }
//
//    @Test
//    public void createProject() throws DatatypeConfigurationException {
//        final ProjectDto project = new ProjectDto();
//        project.setId(projectId);
//        project.setName("proj");
//        project.setDescription("desc");
//        project.setStatus(Status.PROCESS);
//        project.setUserId(session.getUserId());
//        project.setDateStart(convert(new Date()));
//        project.setDateEnd(convert(new Date()));
//        projectEndPoint.persistProject(session, project);
//        assertNotNull(projectEndPoint.findOneProject(session, projectId));
//    }
//
//    @Test
//    public void updateProject() {
//        final ProjectDto project = projectEndPoint.findOneProject(session, projectId);
//        project.setDescription("newDesc");
//        projectEndPoint.mergeProject(session, project);
//        assertEquals(projectEndPoint.findOneProject(session, projectId).getDescription(), "newDesc");
//    }
//
//    @Test
//    public void createTask() throws DatatypeConfigurationException {
//        final TaskDto task = new TaskDto();
//        task.setId(taskId);
//        task.setName("task");
//        task.setDescription("desc");
//        task.setUserId(session.getUserId());
//        task.setStatus(Status.PLANNING);
//        task.setDateStart(convert(new Date()));
//        task.setDateEnd(convert(new Date()));
//        task.setProjectId(projectEndPoint.findOneProject(session, projectId).getId());
//        taskEndPoint.persistTask(session, task);
//        assertNotNull(taskEndPoint.findOneTask(session, taskId));
//    }
//
//    @Test
//    public void updateTask() {
//        final TaskDto task = taskEndPoint.findOneTask(session, taskId);
//        task.setDescription("newDesc");
//        taskEndPoint.mergeTask(session, task);
//        assertEquals(taskEndPoint.findOneTask(session, taskId).getDescription(), "newDesc");
//    }
//
//    @Test
//    public void deleteProjectCascadeTask() {
//        final ProjectDto project = projectEndPoint.findOneProject(session, projectId);
//        projectEndPoint.removeProject(session, project);
//        assertNull(projectEndPoint.findOneProject(session, projectId));
//        assertNull(taskEndPoint.findOneTask(session, taskId));
//    }
//}