package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class ProjectService implements IProjectService {

    @NonNull private EntityManagerFactory entityManagerFactory;

    @Override
    public void persist(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            projectRepository.persist(project);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            projectRepository.merge(project);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            projectRepository.remove(project);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.findOne(userId);
            projectRepository.removeAllByUserId(userId);
            em.getTransaction().commit();
        } catch (UserNotFoundException e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) {
        if (userId.isEmpty() || projectId.isEmpty()) { return null; }
        Project project = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            project = projectRepository.findOne(userId, projectId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return project;
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        List<Project> projects = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            projects = projectRepository.findAllByUserId(userId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return projects;
    }

    @Nullable
    @Override
    public List<Project> findAll() {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final List<Project> projects = projectRepository.findAll();
        em.close();
        return projects;
    }

    @Nullable
    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if (userId.isEmpty() || value.isEmpty()) { return null; }
        List<Project> projects = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            userRepository.findOne(userId);
            projects = projectRepository.findAllSortedByValue(userId, value);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return projects;
    }
}