package ru.itis.services;

import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.Auto;
import ru.itis.models.User;

import java.util.List;

public class UsersService {
    private UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public String showNameUsersById(int i) {
        try{
            User user = usersDao.find(i);
            return user.getName();
        } catch (UserNotFoundException e) {
            String notFound = "Пользователь с id="+i+" не найден!";
            return notFound;
        }
    }

    public String showModelAllUsersAutoById(int i) {
        try{
            User user = usersDao.find(i);
            List<Auto> autoList = user.getListAuto();
            if (autoList.size()==0){
                String autoNotExists = user.getName()+" не пользуется автомобилем!";
                return autoNotExists;
            } else {
                String result = "";
                for (Auto auto : autoList) {
                    result += auto.getModel() + " ";
                }
                return result.trim();
            }
        }catch (UserNotFoundException e){
            String notFound ="Пользователь с id="+i+" не найден!";
            return notFound;
        }
    }

    public boolean isRegistered (int id) {
        try {
            usersDao.find(id);
            return true;
        }catch (UserNotFoundException e) {
            return false;
        }
    }


    public List<User> findAllUsers(){
        return usersDao.findAll();
    }
}
