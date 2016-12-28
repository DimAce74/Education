package ru.itis.dao.files;

import ru.itis.models.Auto;
import ru.itis.dao.AutoDao;
import ru.itis.exceptions.AutoNotFoundException;
import ru.itis.exceptions.SavingAutoException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AutoDaoFileBasedImpl implements AutoDao {

    private File autoFile;
    private static final String SEPARATOR = "\t";

    public AutoDaoFileBasedImpl(File autoFile) {
        this.autoFile = autoFile;
    }

    @Override
    public Auto find(int id) {
        List<Auto> autoList = new ArrayList<>();
        String autoAsString;
        try (BufferedReader reader = new BufferedReader(new FileReader(autoFile))){
            while ((autoAsString=reader.readLine())!=null) {
                String[] autoParams = autoAsString.split(SEPARATOR);
                Auto auto = new Auto (autoParams[1], autoParams[2],Integer.parseInt(autoParams[3]));
                auto.setId(Integer.parseInt(autoParams[0]));
                autoList.add(auto);
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        Auto auto1 = null;
        for (Auto auto : autoList) {
            if (auto.getId() == id) {
                auto1 =  auto;
            }
        }
        if (auto1 == null) {
            throw new AutoNotFoundException();
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
                    throw new SavingAutoException();
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(autoFile))){
            for (Auto auto2 : autoList) {
                String autoAsString = auto2.getId() + SEPARATOR + auto2.getModel() +SEPARATOR+ auto2.getColor() + SEPARATOR+ auto2.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
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
            throw new AutoNotFoundException();
        }
        autoList.remove(autoToUpdate);
        autoList.add(auto);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(autoFile))){
            for (Auto auto1 : autoList) {
                String autoAsString = auto1.getId() + SEPARATOR + auto1.getModel() +SEPARATOR+ auto1.getColor() + SEPARATOR+ auto1.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
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
            throw new AutoNotFoundException();
        }
        autoList.remove(auto);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(autoFile))){
            for (Auto auto1 : autoList) {
                String autoAsString = auto1.getId() + SEPARATOR + auto1.getModel() +SEPARATOR+ auto1.getColor() + SEPARATOR+ auto1.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return true;
    }

    @Override
    public List<Auto> findAll() {
        List<Auto> autoList = new ArrayList<>();
        String autoAsString;
        try (BufferedReader reader = new BufferedReader(new FileReader(autoFile))){
            while ((autoAsString=reader.readLine())!=null) {
                String[] autoParams = autoAsString.split(SEPARATOR);
                Auto auto = new Auto (autoParams[1], autoParams[2],Integer.parseInt(autoParams[3]));
                auto.setId(Integer.parseInt(autoParams[0]));
                autoList.add(auto);
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return autoList;
    }
}
