package ru.girfanov.tm.util;

import ru.girfanov.tm.service.PropertyService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConnectorUtil {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(PropertyService.loadDataBaseDriver());
            connection = DriverManager.getConnection(PropertyService.loadDataBaseConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}