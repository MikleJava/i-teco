package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

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
    public User findOne(@NotNull final String userId) {
        return em.find(User.class, userId);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT t FROM User t", User.class).getResultList();
    }

    @Nullable
    @Override
    public User findOneByLogin(@NotNull final String login) {
        final List<User> users = em.createQuery("SELECT t FROM User t WHERE t.login = :login", User.class).setParameter("login", login).getResultList();
        for (User user : users) {
            if(user != null) return user;
        }
        return null;
    }
}
