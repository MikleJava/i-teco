package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.util.SpringJpaConfig;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    private User user;

    @NotNull private static final String ID = UUID.randomUUID().toString();
    @NotNull private static final String NAME = "project";
    @NotNull private static final String DESCRIPTION = "desc";
    @NotNull private static final Status STATUS = Status.READY;
    @NotNull private static final Date DATE_START = new Date();
    @NotNull private static final Date DATE_END = new Date();
    @NotNull private static final String VALUE = "status";

    @NotNull private static final String LOGIN = "test";

    @Before
    public void setUp() throws UserNotFoundException {
        user = userService.findOneByLogin(LOGIN);
    }

    @After
    public void setDown() {
        user = null;
    }

    @Test
    public void testPersistProject() throws UserNotFoundException {
        final Project project = new Project();
        project.setId(ID);
        project.setName(NAME);
        project.setDescription(DESCRIPTION);
        project.setStatus(STATUS);
        project.setDateStart(DATE_START);
        project.setDateEnd(DATE_END);
        project.setUser(user);
        projectService.persist(user, project);
        assertNotNull(projectService.findOne(user, ID));
    }

    @Test
    public void testMergeProject() throws UserNotFoundException {
        final Project project = projectService.findAll().get(0);
        project.setName("newProject");
        project.setDescription("newDesc");
        project.setStatus(Status.PLANNING);
        project.setDateStart(new Date());
        project.setDateEnd(new Date());
        projectService.merge(user, project);
        final Project updatedProject = projectService.findAll().get(0);
        assertNotNull(updatedProject);
        assertTrue(project.getName().equals(updatedProject.getName()) &&
                project.getDescription().equals(updatedProject.getDescription()) &&
                project.getStatus().name().equals(updatedProject.getStatus().name()) &&
                project.getDateStart().equals(updatedProject.getDateStart()) && project.getDateEnd().equals(updatedProject.getDateEnd()));
    }

    @Test
    public void testRemoveProject() throws UserNotFoundException {
        final Project project = projectService.findAll().get(0);
        projectService.remove(user, project);
        assertNull(projectService.findOne(user, ID));
    }

    @Test
    public void testRemoveAllByUserId() throws UserNotFoundException {
        projectService.removeAllByUserId(user);
        assertNull(projectService.findAllByUserId(user));
    }

    @Test
    public void testFindOne() {
        assertNotNull(projectService.findAll().get(0));
    }

    @Test
    public void testFindAllByUserId() throws UserNotFoundException {
        assertNotNull(projectService.findAllByUserId(user));
    }

    @Test
    public void testFindAll() {
        assertNotNull(projectService.findAll());
    }

    @Test
    public void testFindAllSortedByValue() throws UserNotFoundException {
        assertNotNull((projectService.findAllSortedByValue(user, VALUE)));
    }
}