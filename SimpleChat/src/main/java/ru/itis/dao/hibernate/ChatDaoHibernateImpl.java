package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatDao;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;

import java.util.List;

@Repository("ChatDao")
public class ChatDaoHibernateImpl implements ChatDao{
    private SessionFactory sessionFactory;
    @Autowired
    public ChatDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Chat find(int id) {
        return getSession().createQuery("from Chat where id = :id", Chat.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public int save(Chat chat) {
        getSession().saveOrUpdate(chat);
        return chat.getId();
    }

    @Override
    public void delete(int id) {
        Chat chat = sessionFactory.getCurrentSession().find(Chat.class, id);
        getSession().delete(chat);
    }

    @Override
    public List<Chat> findAll() {
        return getSession().createQuery("from Chat", Chat.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
