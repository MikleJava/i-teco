package ru.girfanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void persist(@NotNull final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String userId) {
        if(userId.isEmpty()) return null;
        final Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Nullable
    @Override
    public User findOneByLogin(@NotNull final String login) {
        if(login.isEmpty()) return null;
        final Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void merge(@NotNull final String userId, @NotNull final User user) throws UserNotFoundException {
        if(userId.isEmpty() || !userId.equals(user.getId())) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(@NotNull final String userId, @NotNull final User user) throws UserNotFoundException {
        if(userId.isEmpty() || !userId.equals(user.getId())) return;
        if(!userRepository.findById(userId).isPresent()) throw new UserNotFoundException("User not found");
        userRepository.delete(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}