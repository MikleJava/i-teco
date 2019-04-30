package ru.girfanov.tm.service;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;
import ru.girfanov.tm.exception.UserNotFoundException;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(CdiTestRunner.class)
public class TaskServiceTest {
    @Inject
    private TaskService taskService;
    @Inject
    private UserService userService;
    @Inject
    private ProjectService projectService;

    private static User user;
    private static Project project;

    @NotNull
    private static final String ID = UUID.randomUUID().toString();
    @NotNull private static final String NAME = "task";
    @NotNull private static final String DESCRIPTION = "desc";
    @NotNull private static final Status STATUS = Status.READY;
    @NotNull private static final Date DATE_START = new Date();
    @NotNull private static final Date DATE_END = new Date();
    @NotNull private static final String VALUE = "status";

    @NotNull private static final String LOGIN = "test";

    @Before
    public void setUp() throws UserNotFoundException {
        user = userService.findOneByLogin(LOGIN);
        project = projectService.findAllByUserId(user).get(0);
    }

    @After
    public void setDown() {
        user = null;
        project = null;
    }

    @Test
    public void testPersistTask() throws UserNotFoundException {
        final Task task = new Task();
        task.setId(ID);
        task.setName(NAME);
        task.setDescription(DESCRIPTION);
        task.setStatus(STATUS);
        task.setDateStart(DATE_START);
        task.setDateEnd(DATE_END);
        task.setUser(user);
        task.setProject(project);
        taskService.persist(user, task);
    }

    @Test
    public void testMergeTask() throws UserNotFoundException {
        final Task task = taskService.findAll().get(0);
        task.setName("newTask");
        task.setDescription("newDesc");
        task.setStatus(Status.PLANNING);
        task.setDateStart(new Date());
        task.setDateEnd(new Date());
        taskService.merge(user, task);
        final Task updatedTask = taskService.findAll().get(0);
        assertNotNull(updatedTask);
        assertTrue(task.getName().equals(updatedTask.getName()) &&
                task.getDescription().equals(updatedTask.getDescription()) &&
                task.getStatus().name().equals(updatedTask.getStatus().name()) &&
                task.getDateStart().equals(updatedTask.getDateStart()) && task.getDateEnd().equals(updatedTask.getDateEnd()));
    }

    @Test
    public void testRemoveTask() throws UserNotFoundException {
        final Task task = taskService.findAll().get(0);
        taskService.remove(user, task);
        assertNull(taskService.findOne(user, task.getId()));
    }

    @Test
    public void testRemoveAllByUserId() throws UserNotFoundException {
        taskService.removeAllByUserId(user);
        assertNull(taskService.findAllByUserId(user));
    }

    @Test
    public void testFindOne() throws UserNotFoundException {
        assertNotNull(taskService.findAll().get(0));
    }

    @Test
    public void testFindAllByUserId() throws UserNotFoundException {
        assertNotNull(taskService.findAllByUserId(user));
    }
    @Test
    public void testFindAllByProjectId() throws UserNotFoundException {
        assertNotNull(taskService.findAllTasksByProjectId(user, project.getId()));
    }
    
    @Test
    public void testFindAll() {
        assertNotNull(taskService.findAll());
    }

    @Test
    public void testFindAllSortedByValue() throws UserNotFoundException {
        assertNotNull((taskService.findAllSortedByValue(user, VALUE)));
    }
}
