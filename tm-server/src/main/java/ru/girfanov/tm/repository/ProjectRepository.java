package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.enumeration.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@NoArgsConstructor
@RequiredArgsConstructor
public final class ProjectRepository implements IProjectRepository {

    @NonNull Connection connection;

    @NotNull private static final String TABLE = "app_project";

    @NotNull private static final String ID = "id";
    @NotNull private static final String NAME = "name";
    @NotNull private static final String DESCRIPTION = "description";
    @NotNull private static final String STATUS = "status_project";
    @NotNull private static final String DATE_START = "date_start";
    @NotNull private static final String DATE_END = "date_end";
    @NotNull private static final String USER_ID = "user_id";

    @Override
    @SneakyThrows
    public void persist(@NotNull final Project project) {
        @NotNull final String query = "INSERT INTO " + TABLE + " (" +
                ID + ", " +
                NAME + ", " +
                DESCRIPTION + ", " +
                STATUS + ", " +
                DATE_START + ", " +
                DATE_END + ", " +
                USER_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, project.getUuid());
        preparedStatement.setString(2, project.getName());
        preparedStatement.setString(3, project.getDescription());
        preparedStatement.setString(4, project.getStatus().name());
        preparedStatement.setDate(5, (java.sql.Date) project.getDateStart());
        preparedStatement.setDate(6, (java.sql.Date) project.getDateEnd());
        preparedStatement.setString(7, project.getUserId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void merge(@NotNull final Project project) {
        @NotNull final String query = "UPDATE " + TABLE + " SET " +
                NAME + " = ?, " +
                DESCRIPTION + " = ?, " +
                STATUS + " = ?, " +
                DATE_START + " = ?, " +
                DATE_END + " = ?, " +
                USER_ID + " = ? WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, project.getName());
        preparedStatement.setString(2, project.getDescription());
        preparedStatement.setString(3, project.getStatus().name());
        preparedStatement.setDate(4, (java.sql.Date) project.getDateStart());
        preparedStatement.setDate(5, (java.sql.Date) project.getDateEnd());
        preparedStatement.setString(6, project.getUserId());
        preparedStatement.setString(7, project.getUuid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final Project project) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, project.getUuid());
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
    public Project findOne(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ? AND " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, projectId);
        preparedStatement.setString(2, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @Nullable Project project = null;
        while (resultSet.next()) { project = fetch(resultSet); }
        preparedStatement.close();
        return project;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        preparedStatement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final String query = "SELECT * FROM " + TABLE;
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        preparedStatement.close();
        return projects;
    }

    @Override
    @Nullable
    @SneakyThrows
    public List<Project> findAllSortedByValue(@NotNull final String userId, @NotNull final String value) {
        if(value.equals(NAME) || value.equals(DESCRIPTION) || value.equals(STATUS) || value.equals(DATE_START) || value.equals(DATE_END)) {
            @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ? ORDER BY " + value;
            @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
            @NotNull final List<Project> projects = new ArrayList<>();
            while (resultSet.next()) projects.add(fetch(resultSet));
            preparedStatement.close();
            return projects;
        }
        return null;
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setUuid(row.getString(ID));
        project.setName(row.getString(NAME));
        project.setDescription(row.getString(DESCRIPTION));
        project.setStatus(Status.valueOf(row.getString(STATUS)));
        project.setDateStart(row.getDate(DATE_START));
        project.setDateEnd(row.getDate(DATE_END));
        project.setUserId(row.getString(USER_ID));
        return project;
    }
}
