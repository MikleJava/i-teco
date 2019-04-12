package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.enumeration.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class TaskRepository implements ITaskRepository {

    @NonNull Connection connection;

    @NotNull private static final String TABLE = "app_task";

    @NotNull private static final String ID = "id";
    @NotNull private static final String NAME = "name";
    @NotNull private static final String DESCRIPTION = "description";
    @NotNull private static final String STATUS = "status_task";
    @NotNull private static final String DATE_START = "date_start";
    @NotNull private static final String DATE_END = "date_end";
    @NotNull private static final String USER_ID = "user_id";
    @NotNull private static final String PROJECT_ID = "project_id";

    @Override
    @SneakyThrows
    public void persist(@NotNull final Task task) {
        @NotNull final String query = "INSERT INTO " + TABLE + " (" +
                ID + ", " +
                NAME + ", " +
                DESCRIPTION + ", " +
                STATUS + ", " +
                DATE_START + ", " +
                DATE_END + ", " +
                USER_ID + ", " +
                PROJECT_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, task.getUuid());
        preparedStatement.setString(2, task.getName());
        preparedStatement.setString(3, task.getDescription());
        preparedStatement.setString(4, task.getStatus().name());
        preparedStatement.setDate(5, (java.sql.Date) task.getDateStart());
        preparedStatement.setDate(6, (java.sql.Date) task.getDateEnd());
        preparedStatement.setString(7, task.getUserId());
        preparedStatement.setString(8, task.getProjectId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void merge(@NotNull final Task task) {
        @NotNull final String query = "UPDATE " + TABLE + " SET " +
                NAME + " = ?, " +
                DESCRIPTION + " = ?, " +
                STATUS + " = ?, " +
                DATE_START + " = ?, " +
                DATE_END + " = ?, " +
                USER_ID + " = ?, " +
                PROJECT_ID + " = ? WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, task.getName());
        preparedStatement.setString(2, task.getDescription());
        preparedStatement.setString(3, task.getStatus().name());
        preparedStatement.setDate(4, (java.sql.Date) task.getDateStart());
        preparedStatement.setDate(5, (java.sql.Date) task.getDateEnd());
        preparedStatement.setString(6, task.getUserId());
        preparedStatement.setString(7, task.getProjectId());
        preparedStatement.setString(8, task.getUuid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final Task task) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, task.getUuid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @Nullable
    @SneakyThrows
    public Task findOne(@NotNull final String userId, @NotNull final String taskId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ? AND " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, taskId);
        preparedStatement.setString(2, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @Nullable Task task = null;
        while (resultSet.next()) { task = fetch(resultSet); }
        preparedStatement.close();
        return task;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        preparedStatement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAll() {
        @NotNull final String query = "SELECT * FROM " + TABLE;
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        preparedStatement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ? AND " + PROJECT_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        preparedStatement.setString(2, projectId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        preparedStatement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + USER_ID + " = ? AND " + PROJECT_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        preparedStatement.setString(2, projectId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @Nullable
    @SneakyThrows
    public List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if(value.equals(NAME) || value.equals(DESCRIPTION) || value.equals(STATUS) || value.equals(DATE_START) || value.equals(DATE_END)) {
            @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ? ORDER BY " + value;
            @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
            @NotNull final List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) tasks.add(fetch(resultSet));
            preparedStatement.close();
            return tasks;
        }
        return null;
    }

    @Nullable
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setUuid(row.getString(ID));
        task.setName(row.getString(NAME));
        task.setDescription(row.getString(DESCRIPTION));
        task.setStatus(Status.valueOf(row.getString(STATUS)));
        task.setDateStart(row.getDate(DATE_START));
        task.setDateEnd(row.getDate(DATE_END));
        task.setUserId(row.getString(USER_ID));
        task.setProjectId(row.getString(PROJECT_ID));
        return task;
    }
}
