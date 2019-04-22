package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public class ProjectRepository implements IProjectRepository {

    @NonNull private EntityManager em;

    @Override
    public void persist(@NotNull final Project project) {
        em.persist(project);
    }

    @Override
    public void merge(@NotNull final Project project) {
        em.merge(project);
    }

    @Override
    public void remove(@NotNull final Project project) {
        em.remove(project);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        em.createQuery("DELETE FROM Project WHERE user = :user_id").setParameter("user_id", userId);
    }

    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) {
        return em.createQuery("SELECT t FROM Project t WHERE t.user = :user_id AND t.id = :id", Project.class).setParameter("user_id", userId).setParameter("id", projectId).getSingleResult();
    }

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        return em.createQuery("SELECT t FROM Project t WHERE t.user = :user_id", Project.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT t FROM Project t", Project.class).getResultList();
    }

    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        return em.createQuery("SELECT t FROM Project t ORDER BY t." + value, Project.class).getResultList();
    }
}
