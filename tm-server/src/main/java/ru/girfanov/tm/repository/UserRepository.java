package ru.girfanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

@Repository
public interface UserRepository extends EntityRepository<User, String>, IUserRepository {

    @Override
    void persist(@NotNull final User userId);

    @Override
    void merge(@NotNull final User userId);

    @Override
    void remove(@NotNull final User userId);

    @Override
    @Query(value = "SELECT u FROM User u WHERE u.id = :userId", singleResult = SingleResultType.OPTIONAL)
    User findOne(@QueryParam("userId") @NotNull final String userId) throws UserNotFoundException;

    @Override
    @Query(value = "SELECT u FROM User u WHERE u.login = :login", singleResult = SingleResultType.OPTIONAL)
    User findOneByLogin(@QueryParam("login") @NotNull final String login);
}
