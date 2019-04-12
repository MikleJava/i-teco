package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DbConnectorUtil {

    @NotNull private static final String HOST = "127.0.0.1";
    @NotNull private static final String PORT = "3306";
    @NotNull private static final String DB = "tm";
    @NotNull private static final String USER = "root";
    @NotNull private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        final Properties connectionProps = new Properties();
        connectionProps.put("user", USER);
        connectionProps.put("password", PASSWORD);
        connectionProps.put("LoginTimeout", "35");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, connectionProps);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}