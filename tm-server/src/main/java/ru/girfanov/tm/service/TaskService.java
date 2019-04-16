package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.util.MyBatisConnectorUtil;

import java.util.List;

@NoArgsConstructor
public final class TaskService implements ITaskService {

    @Override
    public void persist(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.persist(task);
            sqlSession.commit();
        }
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.merge(task);
            sqlSession.commit();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final Task task) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.remove(task);
            sqlSession.commit();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.removeAllByUserId(userId);
            sqlSession.commit();
        }
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String userId, @NotNull final String projectId) {
        if (userId.isEmpty() || projectId.isEmpty()) { return null; }
        Task task;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            task = taskRepository.findOne(userId, projectId);
        }
        return task;
    }

    @Nullable
    @Override
    public List<Task> findAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        List<Task> tasks;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            tasks = taskRepository.findAllByUserId(userId);
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAll() {
        List<Task> tasks;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            tasks = taskRepository.findAll();
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if (userId.isEmpty() || value.isEmpty()) { return null; }
        List<Task> tasks;
        try (final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            tasks = taskRepository.findAllSortedByValue(userId, value);
        }
        return tasks;
    }

    @Nullable
    @Override
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if(userId.isEmpty() || projectId.isEmpty()) { return null; }
        List<Task> tasks;
        try (final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            tasks = taskRepository.findAllTasksByProjectId(userId, projectId);
        }
        return tasks;
    }

    @Override
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        if (userId.isEmpty() || projectId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.removeAllTasksByProjectId(userId, projectId);
            sqlSession.commit();
        }
    }
}
