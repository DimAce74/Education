import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AutoDaoFileBasedImpl implements AutoDao {
    static final File AUTO_FILE = new File ("auto.txt");


    @Override
    public Auto find(int id) {
        List<Auto> autoList = ReadWriteFiles.readAutoFile();
        Auto auto1 = null;
        for (Auto auto : autoList) {
            if (auto.getId() == id) {
                auto1 =  auto;
            }
        }
        return auto1;
    }

    @Override
    public boolean save(Auto auto) {
        List<Auto> autoList = findAll();
        if (auto.getId()==0){
            List<Integer> autosId = new ArrayList<>();
            if (!autoList.isEmpty()) {
                for (Auto auto1 : autoList){
                    autosId.add (auto1.getId());
                }
                Collections.sort(autosId);
                auto.setId(autosId.get(autosId.size()-1)+1);
            }
        }
        autoList.add(auto);
        ReadWriteFiles.writeAutosFile(autoList);
        return true;
    }

    @Override
    public boolean update(Auto auto) {
        Auto oldAuto = null;
        List<Auto> autoList = findAll();
        for (Auto auto1 : autoList){
            if (auto.getId()==auto1.getId()){
                oldAuto = auto;
            }
        }
        autoList.remove(oldAuto);
        autoList.add(auto);
        ReadWriteFiles.writeAutosFile(autoList);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Auto auto = null;
        List<Auto> autoList = findAll();
        for (Auto auto1 : autoList) {
            if (auto1.getId()==id){
                auto = auto1;
            }
        }
        autoList.remove(auto);
        ReadWriteFiles.writeAutosFile(autoList);
        return true;
    }

    @Override
    public List<Auto> findAll() {
        return ReadWriteFiles.readAutoFile();
    }
/**
    @Override
    public User findUserFromId(int id) {
        UsersDao usersDao = new UsersDaoFileBasedImpl();
        return usersDao.find(id);
    }
 */
}
