package ru.girfanov.tm.util;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
@NoArgsConstructor
public class EntityManagerProducerUtil {

    @NotNull
    private static final String UNIT_NAME = "ENTERPRISE";

    @Inject
    @PersistenceUnitName(UNIT_NAME)
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Produces
    @TransactionScoped
    public EntityManager create() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }
}