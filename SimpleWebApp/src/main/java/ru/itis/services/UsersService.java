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

    public List<Auto> showUsersAutoById(int id){
        return usersDao.find(id).getListAuto();
    }

    public List<User> findAllUsers(){
        return usersDao.findAll();
    }

    public User findUser (int id) { return usersDao.find(id);}

    public boolean updateUser (User user) {
        usersDao.update(user);
        return true;
    }

    public boolean deleteUser (int id) {
        usersDao.delete(id);
        return true;
    }

    public boolean addUser (String name, int age) {
        User user = new User(name, age);
        usersDao.save(user);
        return true;
    }
}
