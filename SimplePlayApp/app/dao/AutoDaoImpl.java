package dao;

import com.avaje.ebean.Ebean;
import models.Auto;

import java.util.List;


/**
 * Created by db2admin on 02.05.2017.
 */
public class AutoDaoImpl implements AutoDao {


    @Override
    public Auto find(int id) {
        return Ebean.find(Auto.class, id);
    }

    @Override
    public void save(Auto auto) {
        Ebean.save(auto);
    }

    @Override
    public int delete(int id) {
        return Ebean.delete(Auto.class, id);
    }

    @Override
    public List<Auto> findAll() {
        return Ebean.createQuery(Auto.class).findList();

    }

}

