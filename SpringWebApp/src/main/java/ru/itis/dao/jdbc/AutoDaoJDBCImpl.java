package ru.itis.dao.jdbc;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.dao.AutoDao;
import ru.itis.models.Auto;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class AutoDaoJDBCImpl implements AutoDao {

    // language=SQL
    private static final String SQL_INSERT_NEW_AUTO ="INSERT INTO auto(auto_model, auto_color, user_id) VALUES (?, ?, ?)";
    // language=SQL
    private static final String SQL_UPDATE_AUTO ="UPDATE auto SET auto_model=?, auto_color=?, user_id=?  WHERE auto_id=?";
    // language=SQL
    private static final String SQL_DELETE_AUTO_BY_ID ="DELETE FROM auto WHERE auto_id=?";

    private JdbcTemplate template;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public AutoDaoJDBCImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Auto find(int id) {
        Session session = getSession();
        session.beginTransaction();
        //language=HQL
        Auto auto =  session.createQuery("from Auto where id = ?", Auto.class).setParameter(0, id).getSingleResult();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public boolean save(Auto auto) {
            Object[] params = new Object[] {auto.getModel(), auto.getColor(), auto.getUser().getId()};
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
            int rows = template.update(SQL_INSERT_NEW_AUTO, params, types);
            return rows==1;
        }


    @Override
    public boolean update(Auto auto) {
        Object[] params = new Object[] {auto.getModel(), auto.getColor(), auto.getUser().getId(), auto.getId()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT};
        int rows = template.update(SQL_UPDATE_AUTO, params, types);
        return rows==1;
    }

    @Override
    public boolean delete(int id) {
            int rows = template.update(SQL_DELETE_AUTO_BY_ID, new Object[]{id});
            return rows==1;
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
