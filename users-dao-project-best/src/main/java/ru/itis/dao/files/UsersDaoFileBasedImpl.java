package ru.itis.dao.files;

import ru.itis.models.Auto;
import ru.itis.exceptions.SavingUserException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.User;
import ru.itis.dao.UsersDao;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class UsersDaoFileBasedImpl implements UsersDao {

    private static final String SEPARATOR = "\t";

    private File userFile;
    private File autoFile;

    public UsersDaoFileBasedImpl(File userFile, File autoFile) {
        this.userFile = userFile;
        this.autoFile = autoFile;
    }

    @Override
    public User find(int id) {
        List<User> userList = findAll();
        User user1 = null;
        for (User user : userList) {
            if (user.getId() == id) {
                user1 =  user;
            }
        }
        if (user1 == null){
            throw new UserNotFoundException();
        }
        user1.setListAuto(findAllUsersAuto(id));
        return user1;
    }

    @Override
    public boolean save(User user) {
        List<User> userList = findAll();
        List<Integer> usersId = new ArrayList<>();
        if (!userList.isEmpty()) {
            for (User user1 : userList){
                if (user1.getId()==user.getId()){
                    throw new SavingUserException();
                } else {
                    usersId.add (user1.getId());
                }
            }
            Collections.sort(usersId);
            user.setId(usersId.get(usersId.size()-1)+1);
        } else {
            user.setId(1);
        }
        userList.add(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))){
            for (User user2 : userList) {
                String userAsString = user2.getId()+SEPARATOR + user2.getName() + SEPARATOR + user2.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        User userToUpdate = null;
        List<User> userList = findAll();
        for (User user1 : userList) {
            if (user1.getId() == user.getId()) {
                userToUpdate = user1;
            }
        }
        if (userToUpdate == null){
            throw new UserNotFoundException();
        }
        userList.remove(userToUpdate);
        userList.add(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))){
            for (User user2 : userList) {
                String userAsString = user2.getId()+SEPARATOR + user2.getName() + SEPARATOR + user2.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        List<User> userList = findAll();
        User user = null;
        for (User user1 : userList) {
            if (user1.getId() == id) {
                user = user1;
            }
        }
        if (user == null){
            throw new UserNotFoundException();
        }
        userList.remove(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))){
            for (User user1 : userList) {
                String userAsString = user1.getId()+SEPARATOR + user1.getName() + SEPARATOR + user1.getAge();
                writer.write(userAsString);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        return true;
    }

    @Override
    public List<User> findAll() {
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
        for (User user : userList) {
            user.setListAuto(findAllUsersAuto(user.getId()));
        }
        return userList;
    }

    public List<Auto> findAllUsersAuto(int id) {
        List<Auto> usersAuto = new ArrayList<>();
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

        for (Auto auto : autoList) {
            if (auto.getUserId() == id) {
                usersAuto.add(auto);
            }
        }
        return usersAuto;
    }


}
