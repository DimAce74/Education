package dao;

import com.google.inject.ImplementedBy;
import models.User;

import java.util.List;

/**
 * Created by db2admin on 02.05.2017.
 */
@ImplementedBy(UserDaoImpl.class)
public interface UsersDao {
    User find(int id);
    void save(User user);
    int delete(int id);
    List<User> findAll();
}
