package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.enumeration.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class UserRepository implements IUserRepository {

    @NonNull Connection connection;

    @NotNull private static final String TABLE = "app_user";

    @NotNull private static final String ID = "id";
    @NotNull private static final String LOGIN = "login";
    @NotNull private static final String PASSWORD = "password";
    @NotNull private static final String ROLE = "role";

    @Override
    @SneakyThrows
    public void persist(@NotNull final User user) {
        @NotNull final String query = "INSERT INTO " + TABLE + " (" +
                ID + ", " +
                LOGIN + ", " +
                PASSWORD + ", " +
                ROLE + ") VALUES (?, ?, ?, ?)";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUuid());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole().name());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void merge(@NotNull final User user) {
        @NotNull final String query = "UPDATE " + TABLE + " SET " +
                LOGIN + " = ?, " +
                PASSWORD + " = ?, " +
                ROLE + " = ?, " + " = ? WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole().name());
        preparedStatement.setString(4, user.getUuid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final User user) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUuid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "DELETE FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    @SneakyThrows
    public User findOne(@NotNull final String userId, @Nullable final String entityId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @Nullable final User user = fetch(resultSet);
        preparedStatement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public List<User> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userId);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<User> users = new ArrayList<>();
        while (resultSet.next()) users.add(fetch(resultSet));
        preparedStatement.close();
        return users;
    }

    @Override
    @SneakyThrows
    public List<User> findAll() {
        @NotNull final String query = "SELECT * FROM " + TABLE;
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @NotNull final List<User> users = new ArrayList<>();
        while (resultSet.next()) users.add(fetch(resultSet));
        preparedStatement.close();
        return users;
    }

    @Override
    @SneakyThrows
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        @NotNull final String query = "SELECT * FROM " + TABLE + " WHERE " + LOGIN + " = ? AND " + PASSWORD + " = ?";
        @NotNull final PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        @NotNull final ResultSet resultSet = preparedStatement.executeQuery();
        @Nullable final User user = fetch(resultSet);
        preparedStatement.close();
        return user;
    }

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setUuid(row.getString(ID));
        user.setLogin(row.getString(LOGIN));
        user.setPassword(row.getString(PASSWORD));
        user.setRole(Role.valueOf(row.getString(ROLE)));
        return user;
    }
}
