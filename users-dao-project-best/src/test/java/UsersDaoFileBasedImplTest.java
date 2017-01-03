/**
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.itis.models.Auto;
import ru.itis.exceptions.SavingUserException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.User;
import ru.itis.dao.UsersDao;
import ru.itis.dao.files.UsersDaoFileBasedImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UsersDaoFileBasedImplTest {
    private static final File USER_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\usersTest.txt");
    private static final File AUTO_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\autoTest.txt");
    private static final User MISHA = new User(1, "Misha", 22);
    private static final User VASYA = new User(2, "Vasya", 33);
    private static final User KOSTYA = new User(3, "Kostya", 44);
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);
    private static final String SEPARATOR = "\t";
    private static List<User> USER_LIST = new ArrayList<>(Arrays.asList(MISHA, VASYA));
    private static final List<Auto> MISHA_AUTO_LIST = new ArrayList<>(Arrays.asList(AUDI));

    private UsersDao usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl(USER_FILE, AUTO_FILE);
        MISHA.setListAuto(new ArrayList<>(Arrays.asList(AUDI)));
        VASYA.setListAuto(new ArrayList<>(Arrays.asList(NISSAN)));
        KOSTYA.setListAuto(new ArrayList<>());
    }

    @After
    public void tearDown() throws Exception {
        MISHA.setAge(22);
        USER_LIST = new ArrayList<>();
        USER_LIST.add(MISHA);
        USER_LIST.add(VASYA);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))){
            for (User user : USER_LIST) {
                String userAsString = user.getId()+SEPARATOR + user.getName() + SEPARATOR + user.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
    }

    @Test
    public void testFindUserExists() throws Exception {
        User actual = usersDao.find(1);
        assertEquals(MISHA, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindUserNotExists() throws Exception {
        usersDao.find(4);
    }

    @Test
    public void testSaveUserNotExist() throws Exception {
        usersDao.save(KOSTYA);
        assertEquals(KOSTYA, usersDao.find(3));
    }

    @Test(expected = SavingUserException.class)
    public void testSaveUserAlreadyExist() throws Exception {
        usersDao.save(MISHA);
    }

    @Test
    public void testUpdateUserExists() throws Exception {
        MISHA.setAge(220);
        usersDao.update(MISHA);
        assertTrue(usersDao.find(1).getAge()==220);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateUserNotExists() throws Exception {
        usersDao.update(KOSTYA);
    }

    @Test
    public void testDeleteUserExist() throws Exception {
        usersDao.delete(1);
        assertTrue(usersDao.findAll().size()==1);
    }
    @Test(expected = UserNotFoundException.class)
    public void testDeleteUserNotExist() throws Exception {
        usersDao.delete(4);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> userList = usersDao.findAll();
        assertEquals(USER_LIST.hashCode(), userList.hashCode());
    }

    @Test
    public void testFindAllUsersAutoIfUserExist() throws Exception {
        UsersDaoFileBasedImpl usersDaoFileBased = new UsersDaoFileBasedImpl(USER_FILE, AUTO_FILE);
        List<Auto> actual = usersDaoFileBased.findAllUsersAuto(1);
        assertEquals(MISHA_AUTO_LIST, actual);
    }
}
 */