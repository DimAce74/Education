package dao;

import com.avaje.ebean.Ebean;
import models.User;

import java.util.List;

/**
 * Created by db2admin on 02.05.2017.
 */
public class UserDaoImpl implements UsersDao {


    @Override
    public User find(int id) {
        return Ebean.find(User.class, id);
    }

    @Override
    public void save(User user) {
        Ebean.save(user);
    }

    @Override
    public int delete(int id) {
        return Ebean.delete(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return Ebean.createQuery(User.class).findList();
    }

}


