import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReadWriteFiles.class)
public class AutoDaoFileBasedImplTest {

    private static final File AUTO_FILE = new File ("D:\\Development\\Education\\users-dao-project-best\\src\\test\\autoTest.txt");
    private static final Auto NISSAN = new Auto (1, "Nissan", "red", 2);
    private static final Auto AUDI = new Auto (2, "Audi", "blue", 1);
    private static final Auto LADA = new Auto (3, "Lada", "black", 2);

    private static List<Auto> AUTO_LIST = new ArrayList<>(Arrays.asList(NISSAN, AUDI));

    AutoDao autoDao;

    @Before
    public void setUp() throws Exception {
        autoDao = new AutoDaoFileBasedImpl(AUTO_FILE);

        PowerMockito.mockStatic(ReadWriteFiles.class);
        PowerMockito.when(ReadWriteFiles.readAutoFile(autoDao.getAutoFile())).thenReturn(AUTO_LIST);
        PowerMockito.doNothing().when(ReadWriteFiles.class, "writeAutosFile", any(), any());

    }

    @After
    public void tearDown() throws Exception {
        AUTO_LIST = new ArrayList<>(Arrays.asList(NISSAN, AUDI));
    }

    @Test
    public void testFindAutoExists() throws Exception {
        Auto auto = autoDao.find(1);
        assertEquals(NISSAN, auto);
    }
    @Test(expected = IllegalAccessError.class)
    public void testFindAutoNotExists() throws Exception {
        Auto auto = autoDao.find(4);
    }

    @Test
    public void testSaveAutoNotExists() throws Exception {
        Auto auto = new Auto ("Lada", "blue", 1);
        assertTrue(autoDao.save(auto));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeAutosFile(any(), any());
    }

    @Test
    public void testSaveAutoExists() throws Exception {
        Auto auto = new Auto (1,"Lada", "blue", 1);
        assertFalse(autoDao.save(auto));
    }

    @Test
    public void testUpdateAutoExists() throws Exception {
        NISSAN.setColor("black");
        assertTrue(autoDao.update(NISSAN));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeAutosFile(any(), any());
    }

    @Test
    public void testUpdateAutoNotExists() throws Exception {
        assertFalse(autoDao.update(LADA));
    }

    @Test
    public void testDeleteAutoExists() throws Exception {
        assertTrue(autoDao.delete(1));
        PowerMockito.verifyStatic();
        ReadWriteFiles.writeAutosFile(any(), any());
    }
    @Test
    public void testDeleteAutoNotExists() throws Exception {
        assertFalse(autoDao.delete(4));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Auto> autoList = autoDao.findAll();
        PowerMockito.verifyStatic();
        ReadWriteFiles.readAutoFile(any());
    }

}