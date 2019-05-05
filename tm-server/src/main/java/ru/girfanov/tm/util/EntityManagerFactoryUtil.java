package ru.girfanov.tm.util;

import lombok.NoArgsConstructor;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Session;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.service.PropertyService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@NoArgsConstructor
public class EntityManagerFactoryUtil {
    @Produces
    public static EntityManagerFactory factory() {
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, PropertyService.getJdbcDriver());
        settings.put(Environment.URL, PropertyService.getJdbcUrl());
        settings.put(Environment.USER, PropertyService.getJdbcUsername());
        settings.put(Environment.PASS, PropertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, PropertyService.getJdbcDialect());
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.FORMAT_SQL, "true");
        settings.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        settings.put(Environment.USE_QUERY_CACHE, "true");
        settings.put(Environment.USE_MINIMAL_PUTS, "true");
        settings.put("hibernate.cache.hazelcast.use_lite_member", "true");
        settings.put(Environment.CACHE_REGION_PREFIX, "task-manager");
        settings.put(Environment.CACHE_PROVIDER_CONFIG, "hazelcast.xml");
        settings.put(Environment.CACHE_REGION_FACTORY, "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

}