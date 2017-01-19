package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.dao.UsersDao;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.Auto;
import ru.itis.models.User;

import java.util.List;

@Component
public class UsersService {
@Autowired
    private UsersDao usersDao;

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
