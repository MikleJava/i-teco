package ru.girfanov.tm.service;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyService {

    @Nullable
    public static String getJdbcUsername() {
        @Nullable String name = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            name = properties.getProperty("db.user");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    @Nullable
    public static String getJdbcPassword() {
        @Nullable String password = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            password = properties.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return password;
    }

    @Nullable
    public static String getJdbcUrl() {
        @Nullable String url = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("db.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return url;
    }

    @Nullable
    public static String loadDataBaseConnection() {
        @Nullable String result = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            result =
                    properties.getProperty("db.url") +
                    properties.getProperty("db.host") + ":" +
                    properties.getProperty("db.port") + "/" +
                    properties.getProperty("db.name") + "?user=" +
                    properties.getProperty("db.user") + "&password=" +
                    properties.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Nullable
    public static String getJdbcDriver() {
        @Nullable String driver = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            driver = properties.getProperty("db.driver");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    @Nullable
    public static String getSalt() {
        @Nullable String salt = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            salt = properties.getProperty("salt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return salt;
    }

    @Nullable
    public static String getCycle() {
        @Nullable String cycle = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            cycle = properties.getProperty("cycle");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cycle;
    }
}
