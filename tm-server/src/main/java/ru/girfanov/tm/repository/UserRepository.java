package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    @NonNull private EntityManager em;

    @Override
    public void persist(@NotNull final User user) {
        em.persist(user);
    }

    @Override
    public void merge(@NotNull final User user) {
        em.merge(user);
    }

    @Override
    public void remove(@NotNull final User user) {
        em.remove(user);
    }

    @Override
    public User findOne(@NotNull final String userId) throws UserNotFoundException {
        if(em.find(User.class, userId) == null) throw new UserNotFoundException("user not found");
        return em.find(User.class, userId);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT t FROM User t", User.class).getResultList();
    }

    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        return em.createQuery("SELECT t FROM User t WHERE t.login = :login AND t.password = :password_hash", User.class).setParameter("login", login).setParameter("password_hash", password).getSingleResult();
    }
}
