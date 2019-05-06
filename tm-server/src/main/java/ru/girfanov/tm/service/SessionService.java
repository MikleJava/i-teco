package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongHostException;
import ru.girfanov.tm.exception.WrongPortException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.repository.SessionRepository;
import ru.girfanov.tm.repository.UserRepository;
import ru.girfanov.tm.util.SignatureUtil;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;

import static ru.girfanov.tm.util.DateFormatUtil.getDateISO8601;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
@NoArgsConstructor
public class SessionService implements ISessionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Nullable
    private static String SALT = PropertyService.getSalt();
    @Nullable
    private static Integer CYCLE = Integer.valueOf(PropertyService.getCycle());

    @Override
    @Nullable
    public Session createSession(@NotNull final String login) throws UserNotFoundException {
        Session session = null;
        try {
            final Optional<User> user = userRepository.findByLogin(login);
            if(!user.isPresent()) throw new UserNotFoundException("User not found");
            session = new Session();
            session.setTimestamp(getDateISO8601(new Date()));
            session.setUser(user.get());
            session.setSignature(SignatureUtil.sign(session.getId() + session.getTimestamp(), SALT, CYCLE));
            sessionRepository.save(session);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    @Override
    public void removeSession(@NotNull final Session session) throws UserNotFoundException {
        if(!userRepository.findById(session.getUser().getId()).isPresent()) throw new UserNotFoundException("User not found");
        sessionRepository.delete(session);
    }

    @Override
    public boolean existSession(@NotNull final SessionDto sessionDto) throws WrongSessionException {
        final String signature = sessionDto.getSignature();
        if (signature == null || signature.isEmpty()) throw new WrongSessionException("Wrong session");
        sessionDto.setSignature(null);
        if (!signature.equals(SignatureUtil.sign(sessionDto.getId() + sessionDto.getTimestamp(), SALT, CYCLE))) throw new WrongSessionException("Wrong session");
        sessionDto.setSignature(signature);
        return true;
    }

    @Override
    public String getServerInfo() throws WrongPortException, WrongHostException {
        final String host = System.getProperty("server.host", "localhost");
        if(host == null || host.isEmpty()) throw new WrongHostException("Wrong host");
        final String port = System.getProperty("server.port", "8080");
        if(port == null || port.isEmpty()) throw new WrongPortException("Wrong port");
        return "HOST : " + host + "\n" + "PORT : " + port;
    }
}
