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

@Transactional
@Repository("ChatDao")
public class ChatDaoHibernateImpl implements ChatDao{
    private SessionFactory sessionFactory;
    @Autowired
    public ChatDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Chat find(int id) {
        Session session = getSession();
        //language=HQL
        Chat chat =session.createQuery("from Chat where id = ?", Chat.class)
                .setParameter(0, id).getSingleResult();
        session.merge(chat);
        session.flush();
        session.evict(chat);
        return chat;
    }

    @Override
    public int save(Chat chat) {
        Session session = getSession();
        session.saveOrUpdate(chat);
        session.evict(chat);
        return chat.getId();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = getSession();
        Chat chat = session.find(Chat.class, id);
        session.beginTransaction();
        for(ChatUser chatUser: chat.getChatUserList()){
            session.delete(chatUser);
        }
        session.delete(chat);

        //session.merge(chat);
        session.flush();
        session.evict(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        Session session = getSession();
        //language=HQL
        List<Chat> chats=session.createQuery("from Chat", Chat.class).list();
        for (Chat chat : chats){
            session.evict(chat);
        }
        return chats;
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
