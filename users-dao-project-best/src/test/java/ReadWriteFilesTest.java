
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.rules.TemporaryFolder;
import ru.itis.Auto;
import ru.itis.User;
import ru.itis.dao.files.ReadWriteFiles;

import static org.junit.Assert.*;


public class ReadWriteFilesTest {

    private static final File USER_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\usersTest.txt");
    private static final File AUTO_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\autoTest.txt");
    private static final User MISHA = new User(1, "Misha", 22);
    private static final User VASYA = new User(2, "Vasya", 33);
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);

    private static List<User> USER_LIST = Arrays.asList(MISHA, VASYA);
    private static List<Auto> AUTO_LIST = Arrays.asList(NISSAN, AUDI);



    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testReadUserFile() throws Exception {
        List<User> actual = ReadWriteFiles.readUserFile(USER_FILE);
        boolean isUsersEquals = false;
        boolean isListEquals = false;
        for (int i=0; i<USER_LIST.size(); i++){
            if (actual.get(i).getId()==USER_LIST.get(i).getId()&&
                    actual.get(i).getName().equals(USER_LIST.get(i).getName()) &&
                    actual.get(i).getAge()==USER_LIST.get(i).getAge() &&
                    actual.get(i).getListAuto()==(USER_LIST.get(i).getListAuto())){
                isUsersEquals = true;
            }
        }
        if (USER_LIST.size()==actual.size()&&isUsersEquals){
            isListEquals=true;
        }

        assertTrue(isListEquals);
    }

    @Test
    public void testReadAutoFile() throws Exception {
        List<Auto> actual = ReadWriteFiles.readAutoFile(AUTO_FILE);
        boolean isAutoEquals = false;
        boolean isListEquals = false;
        for (int i=0; i<AUTO_LIST.size(); i++){
            if (actual.get(i).getId()==AUTO_LIST.get(i).getId()&&
                    actual.get(i).getModel().equals(AUTO_LIST.get(i).getModel()) &&
                    actual.get(i).getColor().equals(AUTO_LIST.get(i).getColor()) &&
                    actual.get(i).getUserId()==AUTO_LIST.get(i).getUserId()){
                isAutoEquals = true;
            }
        }
        if (AUTO_LIST.size()==actual.size()&&isAutoEquals){
            isListEquals=true;
        }

        assertTrue(isListEquals);
    }


    @Test
    public void writeUsersFile() throws Exception {
        File tempFile = folder.newFile("temp.txt");
        ReadWriteFiles.writeUsersFile(USER_LIST, tempFile);
        assertTrue(FileUtils.contentEquals(USER_FILE, tempFile));

    }

    @Test
    public void writeAutosFile() throws Exception {
        File tempFile = folder.newFile("temp.txt");
        ReadWriteFiles.writeAutosFile(AUTO_LIST, tempFile);
        assertTrue(FileUtils.contentEquals(AUTO_FILE, tempFile));

    }

}