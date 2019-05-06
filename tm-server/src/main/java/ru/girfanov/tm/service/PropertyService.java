package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@NoArgsConstructor
public class PropertyService {

    @Nullable
    public static String getApplicationHost() {
        @Nullable String host = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            host = properties.getProperty("app.host");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return host;
    }

    @Nullable
    public static String setApplicationPort(@NotNull final String key, @NotNull final String value) {
        @Nullable String port = null;
        try (InputStream input = PropertyService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            properties.setProperty(key, value);
            port = properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return port;
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
