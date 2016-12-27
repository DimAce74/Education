package ru.itis.dao.files;

import ru.itis.Auto;
import ru.itis.Exceptions.UserNotFoundException;
import ru.itis.User;
import ru.itis.dao.UsersDao;
import ru.itis.dao.files.ReadWriteFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class UsersDaoFileBasedImpl implements UsersDao {


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
        return user1;
    }

    @Override
    public boolean save(User user) {
        List<User> userList = findAll();
        List<Integer> usersId = new ArrayList<>();
        if (!userList.isEmpty()) {
            for (User user1 : userList){
                if (user1.getId()==user.getId()){
                    System.out.println("User with id="+user.getId()+" already exist!");
                    return false;
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
        ReadWriteFiles.writeUsersFile(userList, this.userFile);
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
            System.out.println("User "+user.getName()+" not found!");
            return false;
        }
        userList.remove(userToUpdate);
        userList.add(user);
        ReadWriteFiles.writeUsersFile(userList, this.userFile);
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
            System.out.println("User with id="+id+" not found!");
            return false;
        }
        userList.remove(user);

        ReadWriteFiles.writeUsersFile(userList, this.userFile);
        return true;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = ReadWriteFiles.readUserFile(this.userFile);
        for (User user : userList) {
            user.setListAuto(findAllUsersAuto(user.getId()));
        }

        return userList;
    }

    @Override
    public List<Auto> findAllUsersAuto(int id) {
        List<Auto> usersAuto = new ArrayList<>();
        List<Auto> autos = ReadWriteFiles.readAutoFile(this.autoFile);
        for (Auto auto : autos) {
            if (auto.getUserId() == id) {
                usersAuto.add(auto);
            }
        }
        return usersAuto;
    }
}
