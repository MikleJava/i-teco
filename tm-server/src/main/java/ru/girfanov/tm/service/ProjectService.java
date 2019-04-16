package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.util.MyBatisConnectorUtil;
import java.util.List;

@NoArgsConstructor
public final class ProjectService implements IProjectService {

    @Override
    public void persist(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.persist(project);
            sqlSession.commit();
        }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.merge(project);
            sqlSession.commit();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Project project) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.remove(project);
            sqlSession.commit();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projectRepository.removeAllByUserId(userId);
            sqlSession.commit();
        }
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) {
        if (userId.isEmpty() || projectId.isEmpty()) { return null; }
        Project project;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            project = projectRepository.findOne(userId, projectId);
        }
        return project;
    }

    @Nullable
    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        List<Project> projects;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projects = projectRepository.findAllByUserId(userId);
        }
        return projects;
    }

    @Nullable
    @Override
    public List<Project> findAll() {
        List<Project> projects;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projects = projectRepository.findAll();
        }
        return projects;
    }

    @Nullable
    @Override
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if (userId.isEmpty() || value.isEmpty()) { return null; }
        List<Project> projects;
        try (final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final ProjectRepository projectRepository = sqlSession.getMapper(ProjectRepository.class);
            projects = projectRepository.findAllSortedByValue(userId, value);
        }
        return projects;
    }
}