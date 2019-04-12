package ru.girfanov.tm.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;
import ru.girfanov.tm.util.SignatureUtil;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.entity.Session;

import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
public final class SessionService extends AbstractService<Session> implements ISessionService {

    @NonNull private ISessionRepository sessionRepository;
    @NonNull private IUserRepository userRepository;

    @NotNull private static final String SALT = "SALT";
    @NotNull private static final Integer CYCLE = 100;

    @Override
    public Session createSession(@NotNull final String login, @NotNull final String password) throws UserNotFoundException {
        @Nullable final User user = userRepository.findOneByLoginAndPassword(login, password);
        if(user == null) throw new UserNotFoundException("User not found");
        final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setUserId(user.getUuid());
        session.setSignature(SignatureUtil.sign(session, SALT, CYCLE));
        sessionRepository.persist(session);
        return session;
    }

    @Override
    public void removeSession(@NotNull final Session session) throws WrongSessionException {
        existSession(session);
        sessionRepository.remove(session);
    }

    @Override
    public boolean existSession(@NotNull final Session session) throws WrongSessionException {
        final String signature = session.getSignature();
        if(signature == null || signature.isEmpty()) throw new WrongSessionException("Wrong session");
        session.setSignature(null);
        if(!signature.equals(SignatureUtil.sign(session, SALT, CYCLE))) throw new WrongSessionException("Wrong session");
        session.setSignature(signature);
        return true;
    }
}
