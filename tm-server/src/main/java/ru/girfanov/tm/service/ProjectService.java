package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.temp.ProjectRepository;
import ru.girfanov.tm.repository.temp.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Inject private EntityManagerFactory entityManagerFactory;

    @Override
    public void persist(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projectRepository.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projectRepository.merge(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@NotNull final User userId, @NotNull final Project project) throws UserNotFoundException {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projectRepository.remove(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projectRepository.removeAllByUser(userId);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final User userId, @NotNull final String projectId) throws UserNotFoundException {
        Project project = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            project = projectRepository.findOne(userId, projectId);
        } finally {
            em.close();
        }
        return project;
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final User userId) throws UserNotFoundException {
        List<Project> projects = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projects = projectRepository.findAllByUser(userId);
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
    public List<Project> findAllSortedByValue(@NotNull final User userId, @NotNull final String value) throws UserNotFoundException {
        if (value.isEmpty()) { return null; }
        List<Project> projects = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final ProjectRepository projectRepository = new ProjectRepository(em);
        final UserRepository userRepository = new UserRepository(em);
        try {
            if(userRepository.findOne(userId.getId()) == null) throw new UserNotFoundException("user not found");
            projects = projectRepository.findAllSortedByValue(userId, value);
        } finally {
            em.close();
        }
        return projects;
    }
}