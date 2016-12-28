package ru.itis.services;

import ru.itis.models.Auto;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.User;
import ru.itis.dao.UsersDao;

import java.util.List;

//TODO: Сделать добавление пользователя
public class UsersService {
    private UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public String ShowNameUsersById(int i) {
        try{
            User user = usersDao.find(i);
            return user.getName();
        } catch (UserNotFoundException e) {
            String notFound = "Пользователь с id="+i+" не найден!";
            return notFound;
        }
    }

    public String ShowModelAllUsersAutoById(int i) {
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
}
