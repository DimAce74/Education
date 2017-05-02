package dao;

import models.Auto;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

import static play.db.jpa.JPA.em;

/**
 * Created by db2admin on 02.05.2017.
 */
public class AutoDaoImpl implements AutoDao {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AutoDaoImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public Auto find(int id) {
        return em().find(Auto.class, id);
    }

    @Override
    public void save(Auto auto) {
        em().persist(auto);
    }

    @Override
    public int delete(int id) {
        return em().createQuery("delete from Auto a where a.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Auto> findAll() {

        return em().createQuery("select a from Auto a").getResultList();

    }

}

