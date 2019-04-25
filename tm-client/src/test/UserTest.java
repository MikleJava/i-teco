//import org.jetbrains.annotations.NotNull;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import ru.girfanov.tm.endpoint.*;
//
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//
//public class UserTest {
//
//    private static UserEndPoint userEndPoint;
//    private static SessionEndPoint sessionEndPoint;
//    private static Session session;
//    private static User user;
//
//    @NotNull private static final String LOGIN = "test";
//    @NotNull private static final String PASSWORD = "test";
//    @NotNull private static final String ROLE = "USER";
//
//    @BeforeClass
//    public static void setUp() {
//        userEndPoint = new UserEndPointService().getUserEndPointPort();
//        sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();
//        user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setLogin(LOGIN);
//        user.setPassword(PASSWORD);
//        user.setRole(Role.valueOf(ROLE));
//        userEndPoint.persistUser(user);
//        session = sessionEndPoint.createSession(LOGIN);
//    }
//
//    @Test
//    public void testFindByLoginPassword() {
//        assertNotNull(userEndPoint.findOneUserByLogin(LOGIN));
//    }
//
//    @Test
//    public void testFind() {
//        assertNotNull(userEndPoint.findOneUser(session, user.getId()));
//    }
//
//    public void testRemove() {
//        userEndPoint.removeUser(session, user);
//        assertNull(userEndPoint.findOneUserByLogin(LOGIN));
//    }
//}
