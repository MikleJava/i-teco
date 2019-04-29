package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

public interface UserRepository extends EntityRepository<User, String>, IUserRepository {

    @Override
    void persist(@NotNull final User userId);

    @Override
    void merge(@NotNull final User userId);

    @Override
    void remove(@NotNull final User userId);

    @Override
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findOne(@QueryParam("userId") @NotNull final String userId) throws UserNotFoundException;

    @Override
    @Query("SELECT u FROM User u WHERE u.login = :login")
    User findOneByLogin(@QueryParam("login") @NotNull final String login);
}
