package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Status;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

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
    public void setUp() {
        projectService = new ProjectService();
        userService = new UserService();
        user = userService.findOneByLoginAndPassword(LOGIN, PASSWORD);
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
    public void testMerge() {
        final Project project = projectService.findAllByUserId(user.getId()).get(0);
        project.setName("newProject");
        project.setDescription("newDesc");
        project.setStatus(Status.valueOf("PROCESS"));
        project.setDateStart(new Date());
        project.setDateEnd(new Date());
        projectService.merge(user.getId(), project);
        final Project createdProject = projectService.findAllByUserId(user.getId()).get(0);
        assertNotNull(createdProject);
        assertTrue(project.getName().equals(createdProject.getName()) && project.getDescription().equals(createdProject.getDescription()) && project.getStatus().equals(createdProject.getStatus())
                        && project.getDateStart().equals(createdProject.getDateStart()) && project.getDateEnd().equals(createdProject.getDateEnd()));
    }

    @Test
    public void testRemove() {
        final Project project = projectService.findAllByUserId(user.getId()).get(0);
        final String projectId = project.getId();
        projectService.remove(user.getId(), project);
        assertNull(projectService.findOne(user.getId(), projectId));
    }

    @Test
    public void testRemoveAllByUserId() {
        projectService.removeAllByUserId(user.getId());
        assertNull(projectService.findAllByUserId(user.getId()));
    }

    @Test
    public void testFindOne() {
        final Project project = projectService.findAllByUserId(user.getId()).get(0);
        final String projectId = project.getId();
        assertNotNull(projectService.findOne(user.getId(), projectId));
    }

    @Test
    public void testFindAllByUserId() {
        assertNotNull(projectService.findAllByUserId(user.getId()));
    }

    @Test
    public void testFindAll() {
        assertNotNull(projectService.findAll());
    }

    @Test
    public void testFindAllSortedByValue() {
        assertNotNull((projectService.findAllSortedByValue(user.getId(), VALUE)));
    }
}