package ru.itis.dao.files;

import ru.itis.Auto;
import ru.itis.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFiles {

    private static final String SEPARATOR = "\t";

    public static List<User> readUserFile (File userFile) {
        List<User> userList = new ArrayList<>();
        String userAsString;
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))){
                while ((userAsString=reader.readLine())!=null) {
                String[] userParams = userAsString.split(SEPARATOR);
                User user = new User(userParams[1], Integer.parseInt(userParams[2]));
                user.setId(Integer.parseInt(userParams[0]));
                userList.add(user);
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return userList;
    }

    //ru.itis.Auto(int id, String model, String color, ru.itis.User user)
    public static List<Auto> readAutoFile (File autoFile) {
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

    public static void writeUsersFile(List<User> userList, File userFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))){
            for (User user : userList) {
                String userAsString = user.getId()+SEPARATOR + user.getName() + SEPARATOR + user.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
            } catch (Exception e) {
                throw new IllegalAccessError();
        }
    }
    public static void writeAutosFile(List<Auto> autos, File autoFile){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(autoFile))){
            for (Auto auto : autos) {
                String autoAsString = auto.getId() + SEPARATOR + auto.getModel() +SEPARATOR+ auto.getColor() + SEPARATOR+ auto.getUserId();
                writer.write(autoAsString);
                writer.write(System.lineSeparator());
            }
            } catch (Exception e) {
                throw new IllegalAccessError();
        }
    }

}
