package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class SessionRepository implements ISessionRepository {

    @NonNull Connection connection;

    @NotNull private static final String TABLE = "app_session";

    @NotNull private static final String ID = "id";
    @NotNull private static final String TIME_STAMP = "time_stamp";
    @NotNull private static final String SIGNATURE = "signature";
    @NotNull private static final String USER_ID = "user_id";


    @Override
    @SneakyThrows
    public void persist(@NotNull final Session session) {
        @NotNull final String query = "INSERT INTO " + TABLE + " (" +
                ID + ", " +
                TIME_STAMP + ", " +
                SIGNATURE + ", " +
                USER_ID + ") VALUES (?, ?, ?, ?)";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, session.getUuid());
        preparedStatement.setDate(2, (java.sql.Date) session.getTimeStamp());
        preparedStatement.setString(3, session.getSignature());
        preparedStatement.setString(4, session.getUserId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void merge(@NotNull final Session session) {
        @NotNull final String query = "UPDATE " + TABLE + " SET " +
                TIME_STAMP + " = ?, " +
                SIGNATURE + " = ?, " +
                USER_ID + " = ? WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, (java.sql.Date) session.getTimeStamp());
        preparedStatement.setString(2, session.getSignature());
        preparedStatement.setString(3, session.getUserId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final Session session) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, session.getUuid());
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
    @SneakyThrows
    public Session findOne(@NotNull final String userId, @NotNull final String sessionId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ? AND " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, sessionId);
        preparedStatement.setString(2, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @Nullable final Session session = fetch(resultSet);
        preparedStatement.close();
        return session;
    }

    @Override
    @SneakyThrows
    public List<Session> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Session> sessions = new ArrayList<>();
        while (resultSet.next()) sessions.add(fetch(resultSet));
        preparedStatement.close();
        return sessions;
    }

    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final String query = "SELECT * FROM " + TABLE;
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<Session> sessions = new ArrayList<>();
        while (resultSet.next()) sessions.add(fetch(resultSet));
        preparedStatement.close();
        return sessions;
    }

    @Nullable
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Session session = new Session();
        session.setUuid(row.getString(ID));
        session.setTimeStamp(row.getDate(TIME_STAMP));
        session.setSignature(row.getString(SIGNATURE));
        session.setUserId(row.getString(USER_ID));
        return session;
    }
}
