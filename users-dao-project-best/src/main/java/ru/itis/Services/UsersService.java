package ru.itis.Services;

import ru.itis.Auto;
import ru.itis.Exceptions.UserNotFoundException;
import ru.itis.User;
import ru.itis.dao.UsersDao;

import java.util.List;


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
            String notFound = "User with id="+i+" not founded!";
            return notFound;
        }
    }

    public String ShowModelAllUsersAutoById(int i) {
        try{
            List<Auto> autoList = usersDao.findAllUsersAuto(i);
            if (autoList.size()==0){
                String autoNotExists = "User with id=" + i + " do not use Auto!";
                return autoNotExists;
            } else {
                String result = "";
                for (Auto auto : autoList) {
                    result += auto.getModel() + " ";
                }
                return result.trim();
            }
        }catch (UserNotFoundException e){
            String notFound ="User with id="+i+" not founded!";
            return notFound;
        }
    }
}
