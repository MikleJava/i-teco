package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.MyBatisConnectorUtil;
import ru.girfanov.tm.util.PasswordHashUtil;

import java.util.List;

@NoArgsConstructor
public final class UserService implements IUserService {

    @Override
    public void persist(@Nullable final String userId, @NotNull final User user) {
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            user.setPassword(PasswordHashUtil.md5(user.getPassword()));
            userRepository.persist(user);
            sqlSession.commit();
        }
    }

    @Override
    public void merge(@Nullable final String userId, @NotNull final User user) {
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            user.setPassword(PasswordHashUtil.md5(user.getPassword()));
            userRepository.merge(user);
            sqlSession.commit();
        }
    }

    @Override
    public void remove(@Nullable final String userId, @NotNull final User user) {
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.remove(user);
            sqlSession.commit();
        }
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return; }
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.removeAllByUserId(userId);
            sqlSession.commit();
        }
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId, @Nullable final String id) {
        if (userId.isEmpty()) { return null; }
        User user;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            user = userRepository.findOne(userId);
        }
        return user;
    }

    @Nullable
    @Override
    public List<User> findAllByUserId(@NotNull final String userId) {
        if(userId.isEmpty()) { return null; }
        List<User> users;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            users = userRepository.findAllByUserId(userId);
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            users = userRepository.findAll();
        }
        return users;
    }

    @Nullable
    @Override
    public User findOneByLoginAndPassword(@NotNull final String login, @NotNull final String password) {
        if(login.isEmpty() || password.isEmpty()) { return null; }
        User user;
        try(final SqlSession sqlSession = new MyBatisConnectorUtil().getSqlSessionFactory().openSession()) {
            final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            user = userRepository.findOneByLoginAndPassword(login, PasswordHashUtil.md5(password));
        }
        return user;
    }
}
