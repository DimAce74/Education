package dao;

import models.User;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

import static play.db.jpa.JPA.em;

/**
 * Created by db2admin on 02.05.2017.
 */
public class UserDaoImpl implements UsersDao {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public UserDaoImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public User find(int id) {
        return em().find(User.class, id);
    }

    @Override
    public void save(User user) {
        em().persist(user);
    }

    @Override
    public int delete(int id) {
        return em().createQuery("delete from User u where u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> findAll() {

        return em().createQuery("select u from User u").getResultList();

    }

}

