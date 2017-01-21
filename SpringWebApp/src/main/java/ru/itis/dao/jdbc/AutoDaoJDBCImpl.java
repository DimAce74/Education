package ru.itis.dao.jdbc;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.dao.AutoDao;
import ru.itis.models.Auto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("JpaQlInspection")
public class AutoDaoJDBCImpl implements AutoDao {

    // language=SQL
    private static final String SQL_INSERT_NEW_AUTO ="INSERT INTO auto(auto_model, auto_color, user_id) VALUES (:model, :color, :userId)";
    // language=SQL
    private static final String SQL_UPDATE_AUTO ="UPDATE auto SET auto_model=:model, auto_color=:color, user_id=:userId  WHERE auto_id=:autoId";
    // language=SQL
    private static final String SQL_DELETE_AUTO_BY_ID ="DELETE FROM auto WHERE auto_id=:autoId";

    private NamedParameterJdbcTemplate template;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public AutoDaoJDBCImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Auto find(int id) {
        Session session = getSession();
        session.beginTransaction();
        //language=HQL
        Auto auto =  session.createQuery("from Auto where id = ?", Auto.class)
                .setParameter(0, id).getSingleResult();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public int save(Auto auto) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", auto.getModel());
        paramsMap.put("color", auto.getColor());
        paramsMap.put("userId", auto.getUser().getId());
        int rows = template.update(SQL_INSERT_NEW_AUTO, paramsMap);
        return rows;
        }


    @Override
    public int update(Auto auto) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", auto.getModel());
        paramsMap.put("color", auto.getColor());
        paramsMap.put("userId", auto.getUser().getId());
        paramsMap.put("autoId", auto.getUser().getId());
        int rows = template.update(SQL_UPDATE_AUTO, paramsMap);
        return rows;
    }

    @Override
    public int delete(int id) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("autoId", id);
        int rows = template.update(SQL_DELETE_AUTO_BY_ID, paramsMap);
        return rows;
    }

    @Override
    public List<Auto> findAll() {
        Session session = getSession();
        session.beginTransaction();
        //language=HQL
        List<Auto> result =  session.createQuery("from Auto", Auto.class).list();
        session.getTransaction().commit();

        return result;
    }
    private Session getSession() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        return session;
    }
}
