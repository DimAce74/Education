package dao;

import com.google.inject.ImplementedBy;
import models.Auto;

import java.util.List;

/**
 * Created by db2admin on 02.05.2017.
 */
@ImplementedBy(AutoDaoImpl.class)
public interface AutoDao {
    Auto find(int id);
    void save(Auto auto);
    int delete(int id);
    List<Auto> findAll();
}
