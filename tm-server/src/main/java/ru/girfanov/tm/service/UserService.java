package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.PasswordHashUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Transactional
@ApplicationScoped
@NoArgsConstructor
public class UserService implements IUserService {

    @Inject private UserRepository userRepository;

    @Override
    public void persist(@NotNull final String id, @NotNull final String login, @NotNull final String password, @NotNull final Role role) {
        if(id.isEmpty() || login.isEmpty() || password.isEmpty()) { return; }
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(PasswordHashUtil.md5(password));
        user.setRole(role);
        userRepository.persist(user);
    }

    @Override
    public void merge(@NotNull final User user) {
        user.setPassword(PasswordHashUtil.md5(user.getPassword()));
        userRepository.merge(user);
    }

    @Override
    public void remove(@NotNull final User user) {
        userRepository.remove(user);
    }

    @Override
    public User findOne(@NotNull final String userId) throws UserNotFoundException {
        final User user = userRepository.findOne(userId);
        if(user == null) throw new UserNotFoundException("user not found");
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOneByLogin(@NotNull final String login) throws UserNotFoundException {
        final User user = userRepository.findOneByLogin(login);
        if(user == null) throw new UserNotFoundException("user not found");
        return user;
    }
}
