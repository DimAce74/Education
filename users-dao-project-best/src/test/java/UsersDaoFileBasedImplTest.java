import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.itis.Auto;
import ru.itis.Exceptions.UserNotFoundException;
import ru.itis.User;
import ru.itis.dao.UsersDao;
import ru.itis.dao.files.ReadWriteFiles;
import ru.itis.dao.files.UsersDaoFileBasedImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReadWriteFiles.class)
public class UsersDaoFileBasedImplTest {
    private static final File USER_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\usersTest.txt");
    private static final File AUTO_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\autoTest.txt");


    private static final User MISHA = new User(1, "Misha", 22);
    private static final User VASYA = new User(2, "Vasya", 33);
    private static final User KOSTYA = new User(3, "Kostya", 44);
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);

    private static List<User> USERS_LIST = new ArrayList<>(Arrays.asList(MISHA, VASYA));
    private static List<Auto> AUTO_LIST = new ArrayList<>(Arrays.asList(NISSAN, AUDI));

    UsersDao usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl(USER_FILE, AUTO_FILE);

        PowerMockito.mockStatic(ReadWriteFiles.class);
        PowerMockito.when(ReadWriteFiles.readUserFile(USER_FILE)).thenReturn(USERS_LIST);
        PowerMockito.when(ReadWriteFiles.readAutoFile( AUTO_FILE)).thenReturn(AUTO_LIST);
        PowerMockito.doNothing().when(ReadWriteFiles.class, "writeUsersFile", any(), any());
        PowerMockito.doNothing().when(ReadWriteFiles.class, "writeAutosFile", any(), any());
    }

    @After
    public void tearDown() throws Exception {
        USERS_LIST = new ArrayList<>(Arrays.asList(MISHA, VASYA));
        AUTO_LIST = new ArrayList<>(Arrays.asList(NISSAN, AUDI));
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
        User user = new User("Kostya", 44);
        assertTrue(usersDao.save(user));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeUsersFile(any(), any());
    }

    @Test
    public void testSaveUserAlreadyExist() throws Exception {
        assertFalse(usersDao.save(MISHA));
    }

    @Test
    public void testUpdateUserExists() throws Exception {
        MISHA.setAge(220);
        assertTrue(usersDao.update(MISHA));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeUsersFile(any(), any());
    }
    @Test
    public void testUpdateUserNotExists() throws Exception {
        assertFalse(usersDao.update(KOSTYA));
    }

    @Test
    public void testDeleteUserExist() throws Exception {
        assertTrue(usersDao.delete(1));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeUsersFile(any(), any());
    }
    @Test
    public void testDeleteUserNotExist() throws Exception {
        assertFalse(usersDao.delete(4));
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> userList = usersDao.findAll();
        PowerMockito.verifyStatic();
        ReadWriteFiles.readUserFile(any());
        boolean isEquals = false;
        if (userList.get(0).getListAuto().get(0).equals(AUDI) &&
                userList.get(1).getListAuto().get(0).equals(NISSAN)) {
            isEquals = true;
        }
        assertTrue(isEquals);
    }

    @Test
    public void testFindAllUsersAutoIfUserExist() throws Exception {
        List<Auto> autoList = usersDao.findAllUsersAuto(1);
        PowerMockito.verifyStatic();
        ReadWriteFiles.readAutoFile(any());
        assertTrue(autoList.get(0).equals(AUDI));
    }

}