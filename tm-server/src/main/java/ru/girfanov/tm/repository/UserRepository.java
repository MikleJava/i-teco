package ru.girfanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String>, IUserRepository {

    @Override
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = ?2 where u.id = ?1")
    void merge(@NotNull final String userId, @NotNull final String password);

    @Override
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    Optional<User> findByLogin(@NotNull final String login);
}
