import org.junit.Before;
import org.junit.Test;
import ru.itis.models.Auto;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.services.UsersService;
import ru.itis.models.User;
import ru.itis.dao.UsersDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UsersServiceTest {


    private static final User MISHA = new User(1, "Misha", 22);
    private static final User VASYA = new User(2, "Vasya", 33);
    private static final User KOSTYA = new User(3, "Kostya", 44);
    private static final Auto AUDI = new Auto (2, "Audi", "blue");

    private static List<User> USERS_LIST = new ArrayList<>(Arrays.asList(MISHA, VASYA, KOSTYA));
    private static List<Auto> MISHA_AUTO_LIST = new ArrayList<>(Arrays.asList(AUDI));

    UsersDao usersDao;
    UsersService usersService;

    @Before
    public void setUp() throws Exception {
        usersDao = mock (UsersDao.class);
        usersService = new UsersService(usersDao);

        MISHA.setListAuto(MISHA_AUTO_LIST);
        AUDI.setUser(MISHA);
        KOSTYA.setListAuto(new ArrayList<>());
        when(usersDao.findAll()).thenReturn(USERS_LIST);
        when(usersDao.find(1)).thenReturn(MISHA);
        when(usersDao.find(3)).thenReturn(KOSTYA);
        doThrow(new UserNotFoundException()).when(usersDao).find(4);

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
        String expected = "Пользователь с id=4 не найден!";
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
        String expected = "Пользователь с id=4 не найден!";
        assertEquals(expected, actual);
    }
    @Test
    public void testShowAllUsersAutoByIdIfAutoNotExists() throws Exception {
        String actual = usersService.ShowModelAllUsersAutoById(3);
        String expected = "Kostya не пользуется автомобилем!";
        assertEquals(expected, actual);
    }

    @Test
    public void testIsRegisteredUserExists() throws Exception {
        assertTrue(usersService.isRegistered(1));
    }

    @Test
    public void testIsRegisteredUserNotExists() throws Exception {
        assertFalse(usersService.isRegistered(4));
    }
}
