package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatDao;
import ru.itis.models.Chat;

import java.util.List;

@Transactional
@Repository
public class ChatDaoHibernateImpl implements ChatDao{
    private SessionFactory sessionFactory;
    @Autowired
    public ChatDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Chat find(int id) {
        //language=HQL
        return getSession().createQuery("from Chat where id = ?", Chat.class)
                .setParameter(0, id).getSingleResult();
    }

    @Override
    public int save(Chat chat) {
        getSession().saveOrUpdate(chat);
        return chat.getId();
    }

    @Override
    public void delete(int id) {
        Chat chat = find(id);
        getSession().delete(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        //language=HQL
        return getSession().createQuery("from Chat", Chat.class).list();
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
