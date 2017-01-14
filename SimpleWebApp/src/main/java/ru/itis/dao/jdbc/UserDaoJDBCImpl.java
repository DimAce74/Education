package ru.itis.dao.jdbc;


import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.dao.UsersDao;
import ru.itis.hibernate.HibernateConnector;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
public class UserDaoJDBCImpl implements UsersDao {

     // language=SQL
    private static final String SQL_INSERT_NEW_USER_BY_NAME_AND_AGE ="INSERT INTO group_user(user_name, user_age) VALUES (?, ?)";
    // language=SQL
    private static final String SQL_UPDATE_USER ="UPDATE group_user SET user_name=?, user_age=?  WHERE id=?";
    // language=SQL
    private static final String SQL_DELETE_USER_BY_ID ="DELETE FROM group_user WHERE id=?";

    private JdbcTemplate template;
    private Session session;

    public UserDaoJDBCImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public User find(int id) {
        this.session = HibernateConnector.getConnector().getSession();
        session.beginTransaction();
        //language=HQL
        User user =  session.createQuery ("from User where id = :userId", User.class)
                .setParameter("userId", id).getSingleResult();
        session.getTransaction().commit();
            return user;
    }

    @Override
    public boolean save(User user) {
        Object[] params = new Object[] {user.getName(), user.getAge()};
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER};
        int rows = template.update(SQL_INSERT_NEW_USER_BY_NAME_AND_AGE, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) {

        Object[] params = new Object[] {user.getName(), user.getAge(), user.getId()};
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.BIGINT};
        int rows = template.update(SQL_UPDATE_USER, params, types);
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int rows = template.update(SQL_DELETE_USER_BY_ID, new Object[]{id});
        if (rows==1) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        this.session = HibernateConnector.getConnector().getSession();
        session.beginTransaction();
        //language=HQL
        List<User> result =  session.createQuery("from User", User.class).list();
        session.getTransaction().commit();

        return result;
    }

}

