package services;


import com.google.inject.Inject;
import dao.UsersDao;
import models.Auto;
import models.User;

import java.util.List;

public class UsersService {

    @Inject
    private UsersDao usersDao;

    public List<Auto> showUsersAutoById(int id){
        return usersDao.find(id).getListAuto();
    }

    public List<User> findAllUsers(){
        return usersDao.findAll();
    }

    public User findUser (int id) { return usersDao.find(id);}

    public boolean deleteUser (int id) {
        usersDao.delete(id);
        return true;
    }

    public boolean addUser (String name) {
        User user = new User();
        user.setName(name);
        usersDao.save(user);
        return true;
    }
}
