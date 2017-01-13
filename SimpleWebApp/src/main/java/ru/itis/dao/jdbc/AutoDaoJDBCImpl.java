package ru.itis.dao.jdbc;


import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.dao.AutoDao;
import ru.itis.hibernate.HibernateConnector;
import ru.itis.models.Auto;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
public class AutoDaoJDBCImpl implements AutoDao {

    // language=SQL
    private static final String SQL_INSERT_NEW_AUTO ="INSERT INTO auto(auto_model, auto_color, user_id) VALUES (?, ?, ?)";
    // language=SQL
    private static final String SQL_UPDATE_AUTO ="UPDATE auto SET auto_model=?, auto_color=?, user_id=?  WHERE auto_id=?";
    // language=SQL
    private static final String SQL_DELETE_AUTO_BY_ID ="DELETE FROM auto WHERE auto_id=?";

    private JdbcTemplate template;
    private Session session;

    public AutoDaoJDBCImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Auto find(int id) {
        this.session = HibernateConnector.getConnector().getSession();
        session.beginTransaction();

        Auto auto =  session.createQuery ("from Auto where id = :autoId", Auto.class)
                .setParameter("autoId", id).getSingleResult();
        session.getTransaction().commit();
        return auto;
    }

    @Override
    public boolean save(Auto auto) {
            Object[] params = new Object[] {auto.getModel(), auto.getColor(), auto.getUser().getId()};
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
            int rows = template.update(SQL_INSERT_NEW_AUTO, params, types);
            if (rows==1) {
                return true;
            }
            return false;
        }


    @Override
    public boolean update(Auto auto) {
        Object[] params = new Object[] {auto.getModel(), auto.getColor(), auto.getUser().getId(), auto.getId()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT};
        int rows = template.update(SQL_UPDATE_AUTO, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
            int rows = template.update(SQL_DELETE_AUTO_BY_ID, new Object[]{id}, Types.BIGINT);
            if (rows==1) {
                return true;
            }
            return false;
    }

    @Override
    public List<Auto> findAll() {
        this.session = HibernateConnector.getConnector().getSession();
        session.beginTransaction();

        List<Auto> result =  session.createQuery("from Auto", Auto.class).list();
        session.getTransaction().commit();

        return result;
    }

}
