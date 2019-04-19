package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.PasswordHashUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
public final class UserService implements IUserService {

    @NonNull private EntityManagerFactory entityManagerFactory;

    @Override
    public void persist(@NotNull final User user) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            user.setPassword(PasswordHashUtil.md5(user.getPassword()));
            userRepository.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull final User user) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            user.setPassword(PasswordHashUtil.md5(user.getPassword()));
            userRepository.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@NotNull final User user) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public User findOne(@NotNull final String userId) {
        User user = null;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            user = userRepository.findOne(userId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            users = userRepository.findAll();
        } finally {
            em.close();
        }
        return users;
    }

    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        User user;
        final EntityManager em = entityManagerFactory.createEntityManager();
        final UserRepository userRepository = new UserRepository(em);
        try {
            user = userRepository.findOneByLoginAndPassword(login, PasswordHashUtil.md5(password));
        } finally {
            em.close();
        }
        return user;
    }
}
