import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AutoDaoFileBasedImpl implements AutoDao {

    private File autoFile;

    public File getAutoFile() {
        return autoFile;
    }

    public AutoDaoFileBasedImpl(File autoFile) {
        this.autoFile = autoFile;
    }

    @Override
    public Auto find(int id) {
        List<Auto> autoList = ReadWriteFiles.readAutoFile(this.autoFile);
        Auto auto1 = null;
        for (Auto auto : autoList) {
            if (auto.getId() == id) {
                auto1 =  auto;
            }
        }
        if (auto1 == null) {
            throw new IllegalAccessError("Auto with id="+id+" not found!");
        }
        return auto1;
    }

    @Override
    public boolean save(Auto auto) {
        List<Auto> autoList = findAll();
        List<Integer> autosId = new ArrayList<>();
        if (!autoList.isEmpty()) {
            for (Auto auto1 : autoList){
                if (auto.getId()==auto1.getId()) {
                    System.out.println("Auto with id="+auto.getId()+" already exists!");
                    return false;
                }else{
                    autosId.add (auto1.getId());
                }
            }
            Collections.sort(autosId);
            auto.setId(autosId.get(autosId.size()-1)+1);
        }else{
            auto.setId(1);
        }

        autoList.add(auto);
        ReadWriteFiles.writeAutosFile(autoList, this.autoFile);
        return true;
    }

    @Override
    public boolean update(Auto auto) {
        Auto autoToUpdate = null;
        List<Auto> autoList = findAll();
        for (Auto auto1 : autoList){
            if (auto.getId()==auto1.getId()){
                autoToUpdate = auto;
            }
        }
        if (autoToUpdate==null) {
            System.out.println("Auto "+auto.getModel()+" not found!");
            return false;
        }
        autoList.remove(autoToUpdate);
        autoList.add(auto);
        ReadWriteFiles.writeAutosFile(autoList, this.autoFile);
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
        if (auto == null) {
            System.out.println("Auto with id=" + id + " not found!");
            return false;
        }
        autoList.remove(auto);
        ReadWriteFiles.writeAutosFile(autoList, this.autoFile);
        return true;
    }

    @Override
    public List<Auto> findAll() {
        return ReadWriteFiles.readAutoFile(this.autoFile);
    }

}
