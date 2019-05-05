package hazelcast;

import com.hazelcast.core.Hazelcast;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.enumeration.Role;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.service.UserService;

import static org.junit.Assert.*;

import javax.inject.Inject;
import java.util.UUID;

public class SecondLevelCacheTest {

    @NotNull
    private static final String ID = UUID.randomUUID().toString();
    @NotNull private static final String LOGIN = "test";
    @NotNull private static final String PASSWORD = "test";
    @NotNull private static final Role ROLE = Role.USER;

    @Inject
    private UserService userService;

    @Test
    public void createUserTest() throws UserNotFoundException, IllegalAccessException, InstantiationException {
//        userService.persist(ID, LOGIN, PASSWORD, ROLE);
//        final User user = userService.findOne(ID);
//        int size = Hazelcast.newHazelcastInstance().getCacheManager().getCache("ru.girfanov.tm.entity.User").size();
//        assertTrue(size > 0);
    }
}
