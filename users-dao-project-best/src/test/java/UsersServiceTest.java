import org.junit.Before;
import org.junit.Test;
import ru.itis.Auto;
import ru.itis.Services.UsersService;
import ru.itis.User;
import ru.itis.dao.UsersDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UsersServiceTest {
    private static final User MISHA = new User(1, "Misha", 22);
    private static final User VASYA = new User(2, "Vasya", 33);
    private static final User KOSTYA = new User(3, "Kostya", 44);
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);

    private static List<User> USERS_LIST = new ArrayList<>(Arrays.asList(MISHA, VASYA, KOSTYA));
    private static List<Auto> AUTO_LIST = new ArrayList<>(Arrays.asList(NISSAN, AUDI));
    private static List<Auto> MISHA_AUTO_LIST = new ArrayList<>(Arrays.asList(AUDI));

    UsersDao usersDao;
    UsersService usersService;

    @Before
    public void setUp() throws Exception {
        usersDao = mock (UsersDao.class);
        usersService = new UsersService(usersDao);

        when(usersDao.findAll()).thenReturn(USERS_LIST);
        when(usersDao.find(1)).thenReturn(MISHA);
        when(usersDao.find(3)).thenReturn(KOSTYA);
        doThrow(new IllegalAccessError()).when(usersDao).find(4);
        when(usersDao.findAllUsersAuto(1)).thenReturn(MISHA_AUTO_LIST);
        doThrow(new IllegalAccessError()).when(usersDao).findAllUsersAuto(3);
        doThrow(new IllegalAccessError()).when(usersDao).findAllUsersAuto(4);

    }

    @Test
    public void testShowNameUsersByIdIfExists() throws Exception {
        String actual = usersService.ShowNameUsersById(1);
        String expected = MISHA.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testShowNameUsersByIdIfNotExists() throws Exception {
        String actual = usersService.ShowNameUsersById(4);
        String expected = "User with id=4 not founded!";
        assertEquals(expected, actual);
    }

    @Test
    public void testShowModelAllUsersAutoByIdIfExists() throws Exception {
        String actual = usersService.ShowModelAllUsersAutoById(1);
        assertEquals("Audi", actual);
    }

    @Test
    public void testShowAllUsersAutoByIdIfUserNotExists() throws Exception {
        String actual = usersService.ShowModelAllUsersAutoById(4);
        String expected = "User with id=4 not founded!";
        assertEquals(expected, actual);
    }
    @Test
    public void testShowAllUsersAutoByIdIfAutoNotExists() throws Exception {
        String actual = usersService.ShowModelAllUsersAutoById(3);
        String expected = "User with id=3 do not use Auto!";
        assertEquals(expected, actual);
    }
}
