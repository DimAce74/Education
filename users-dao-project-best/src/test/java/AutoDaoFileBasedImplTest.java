import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.itis.Auto;
import ru.itis.dao.AutoDao;
import ru.itis.dao.files.AutoDaoFileBasedImpl;
import ru.itis.exceptions.AutoNotFoundException;
import ru.itis.exceptions.SavingAutoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AutoDaoFileBasedImplTest {

    private static final File AUTO_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\autoTest.txt");
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);
    private static final Auto LADA = new Auto (3, "Lada", "black", 2);
    private static final String SEPARATOR = "\t";


    private static List<Auto> autoList = new ArrayList<>(Arrays.asList(NISSAN, AUDI));

    AutoDao autoDao;

    @Before
    public void setUp() throws Exception {
        autoDao = new AutoDaoFileBasedImpl(AUTO_FILE);

    }

    @After
    public void tearDown() throws Exception {
        autoList = new ArrayList<>(Arrays.asList(NISSAN, AUDI));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUTO_FILE))){
            for (Auto auto2 : autoList) {
                String autoAsString = auto2.getId() + SEPARATOR + auto2.getModel() +SEPARATOR+ auto2.getColor() + SEPARATOR+ auto2.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
    }

    @Test
    public void testFindAutoExists() throws Exception {
        Auto auto = autoDao.find(1);
        assertEquals(NISSAN, auto);
    }
    @Test(expected = AutoNotFoundException.class)
    public void testFindAutoNotExists() throws Exception {
        autoDao.find(4);
    }

    @Test
    public void testSaveAutoNotExists() throws Exception {
        assertTrue(autoDao.save(LADA));

    }

    @Test(expected = SavingAutoException.class)
    public void testSaveAutoExists() throws Exception {
        autoDao.save(NISSAN);
    }

    @Test
    public void testUpdateAutoExists() throws Exception {
        NISSAN.setColor("black");
        assertTrue(NISSAN.getColor().equals("black"));

    }

    @Test(expected = AutoNotFoundException.class)
    public void testUpdateAutoNotExists() throws Exception {
        autoDao.update(LADA);
    }

    @Test
    public void testDeleteAutoExists() throws Exception {
        autoDao.delete(1);
        assertTrue(autoDao.findAll().size()==1);
    }
    @Test(expected = AutoNotFoundException.class)
    public void testDeleteAutoNotExists() throws Exception {
        autoDao.delete(4);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Auto> autoList1 = autoDao.findAll();
        assertEquals(autoList, autoList1);
    }

}