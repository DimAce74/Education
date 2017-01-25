package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.MessageDao;
import ru.itis.models.Message;

import java.util.List;

@Transactional
@Repository
public class MessageDaoHibernateImpl implements MessageDao {

    private SessionFactory sessionFactory;
    @Autowired
    public MessageDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Message find(int id) {
        //language=HQL
        return getSession().createQuery("from Message where id = ?", Message.class)
                .setParameter(0, id).getSingleResult();
    }

    @Override
    public int save(Message message) {
        getSession().saveOrUpdate(message);
        return message.getId();    }

    @Override
    public void delete(int id) {
        Session session = getSession();
        Message message = find(id);
        session.delete(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> findAll() {
        //language=HQL
        return getSession().createQuery("from Message", Message.class).list();    }

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
