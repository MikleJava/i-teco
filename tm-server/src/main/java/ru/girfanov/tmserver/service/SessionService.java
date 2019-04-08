package ru.girfanov.tmserver.service;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.api.repository.ISessionRepository;
import ru.girfanov.tmserver.api.repository.IUserRepository;
import ru.girfanov.tmserver.api.service.ISessionService;
import ru.girfanov.tmserver.entity.Session;
import ru.girfanov.tmserver.entity.User;
import  ru.girfanov.tmserver.util.SignatureUtil;

import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
public class SessionService implements ISessionService {

    @NonNull private ISessionRepository sessionRepository;
    @NonNull private IUserRepository userRepository;

    @NotNull private static final String SALT = "SALT";
    @NotNull private static final Integer CYCLE = 100;

    @Override
    public Session createSession(@NotNull final String login, @NotNull final String password) {
        @Nullable final User user = userRepository.findOneByLoginAndPassword(login, password);
        if(user == null) return null;
        final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setUserId(user.getUuid());
        session.setSignature(SignatureUtil.sign(session, SALT, CYCLE));
        return session;
    }

    @Override
    public void removeSession(@NotNull final String userId, @NotNull final String sessionId) {
        @Nullable final User user = userRepository.findOne(userId, userId);
        @Nullable final Session session = sessionRepository.findOne(userId, sessionId);
        if(user == null || session == null) return;
        sessionRepository.remove(userId, sessionId);
    }

    @Override
    public boolean existSession(@NotNull final String userId, @NotNull final String sessionId) {
        @Nullable final Session session = sessionRepository.findOne(userId, sessionId);
        return session != null;
    }
}
