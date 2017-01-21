package ru.itis.dao.jdbc;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.dao.UsersDao;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("JpaQlInspection")
public class UserDaoJDBCImpl implements UsersDao {

     // language=SQL
    private static final String SQL_INSERT_NEW_USER_BY_NAME_AND_AGE ="INSERT INTO group_user(user_name, user_age) VALUES (:userName, :userAge)";
    // language=SQL
    private static final String SQL_UPDATE_USER ="UPDATE group_user SET user_name=:userName, user_age=:userAge  WHERE id=:userId";
    // language=SQL
    private static final String SQL_DELETE_USER_BY_ID ="DELETE FROM group_user WHERE id=:userId";


    private NamedParameterJdbcTemplate template;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoJDBCImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User find(int id) {
        Session session = getSession();
        session.beginTransaction();
        //language=HQL
        User user =  session.createQuery ("from User where id = ?", User.class)
                .setParameter(0, id).getSingleResult();
        session.getTransaction().commit();
            return user;
    }

    @Override
    public int save(User user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", user.getName());
        paramsMap.put("userAge", user.getAge());
        int rows = template.update(SQL_INSERT_NEW_USER_BY_NAME_AND_AGE, paramsMap);
        return rows;
    }

    @Override
    public int update(User user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", user.getName());
        paramsMap.put("userAge", user.getAge());
        paramsMap.put("userId", user.getId());
        int rows = template.update(SQL_UPDATE_USER, paramsMap);
        return rows;
    }

    @Override
    public int delete(int id) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", id);
        int rows = template.update(SQL_DELETE_USER_BY_ID, paramsMap);
        return rows;
    }

    @Override
    public List<User> findAll() {
        Session session = getSession();
        session.beginTransaction();
        //language=HQL
        List<User> result =  session.createQuery("from User", User.class).list();
        session.getTransaction().commit();

        return result;
    }
    private Session getSession() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }

        return session;
    }


}

