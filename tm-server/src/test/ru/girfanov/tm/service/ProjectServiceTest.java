package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProjectServiceTest {

    private static ProjectService projectService;
    private static UserService userService;
    private User user;

    @NotNull private static final String NAME = "project";
    @NotNull private static final String DESCRIPTION = "desc";
    @NotNull private static final String STATUS = "READY";
    @NotNull private static final Date DATE_START = new Date();
    @NotNull private static final Date DATE_END = new Date();
    @NotNull private static final String VALUE = "status";

    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";

    @BeforeClass
    public void setUp() throws UserNotFoundException {
        projectService = new ProjectService();
        userService = new UserService();
        user = userService.findOneByLogin(LOGIN);
    }

    @Test
    public void testPersist() {
        final Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setName(NAME);
        project.setDescription(DESCRIPTION);
        project.setStatus(Status.valueOf(STATUS));
        project.setDateStart(DATE_START);
        project.setDateEnd(DATE_END);
        project.setUser(user);
    }

    @Test
    public void testMerge() throws UserNotFoundException {
        final Project project = projectService.findAllByUserId(user).get(0);
        project.setName("newProject");
        project.setDescription("newDesc");
        project.setStatus(Status.valueOf("PROCESS"));
        project.setDateStart(new Date());
        project.setDateEnd(new Date());
        projectService.merge(user, project);
        final Project createdProject = projectService.findAllByUserId(user).get(0);
        assertNotNull(createdProject);
        assertTrue(project.getName().equals(createdProject.getName()) && project.getDescription().equals(createdProject.getDescription()) && project.getStatus().equals(createdProject.getStatus())
                        && project.getDateStart().equals(createdProject.getDateStart()) && project.getDateEnd().equals(createdProject.getDateEnd()));
    }

    @Test
    public void testRemove() throws UserNotFoundException {
        final Project project = projectService.findAllByUserId(user).get(0);
        final String projectId = project.getId();
        projectService.remove(user, project);
        assertNull(projectService.findOne(user, projectId));
    }

    @Test
    public void testRemoveAllByUserId() throws UserNotFoundException {
        projectService.removeAllByUserId(user);
        assertNull(projectService.findAllByUserId(user));
    }

    @Test
    public void testFindOne() throws UserNotFoundException {
        final Project project = projectService.findAllByUserId(user).get(0);
        final String projectId = project.getId();
        assertNotNull(projectService.findOne(user, projectId));
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