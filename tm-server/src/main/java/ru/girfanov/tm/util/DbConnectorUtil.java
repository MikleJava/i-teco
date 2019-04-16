package ru.girfanov.tm.util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.repository.ProjectRepository;
import ru.girfanov.tm.repository.SessionRepository;
import ru.girfanov.tm.repository.TaskRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.service.PropertyService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConnectorUtil {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(PropertyService.getJdbcDriver());
            connection = DriverManager.getConnection(PropertyService.loadDataBaseConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        @Nullable final String user = PropertyService.getJdbcUsername();
        @Nullable final String password = PropertyService.getJdbcPassword();
        @Nullable final String url = PropertyService.getJdbcUrl();
        @Nullable final String driver = PropertyService.getJdbcDriver();
        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment = new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserRepository.class);
        configuration.addMapper(ProjectRepository.class);
        configuration.addMapper(SessionRepository.class);
        configuration.addMapper(TaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

}