package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.MessageDao;
import ru.itis.models.Message;

import java.util.List;

@Repository
public class MessageDaoHibernateImpl implements MessageDao {

    private SessionFactory sessionFactory;
    @Autowired
    public MessageDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Message find(int id) {
        return getSession().createQuery("from Message where id = :id", Message.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public int save(Message message) {
        getSession().saveOrUpdate(message);
        return message.getId();    }

    @Override
    public void delete(int id) {
        Message message = find(id);
        getSession().delete(message);
    }

    public List<Message> findAllByChatId(Integer chatId) {
        return getSession().createQuery("from Message where chat.id = :id", Message.class)
                .setParameter("id", chatId).list();
    }

    @Override
    public List<Message> findAll() {
        return getSession().createQuery("from Message", Message.class).list();    }

    @Override
    public List<Message> findNewMessages(Integer chatId, Integer userId){
        Message lastMessage = find(getSession().createNativeQuery("SELECT FROM chat_member WHERE chat_id=:chatId, user_id=:userId", Integer.class)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId)
                .getSingleResult());
        return getSession().createQuery("from Message where chat.id=:chatId and chatUser.id=:userId and id>:messageId", Message.class)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId)
                .setParameter("messageId", lastMessage.getId()).list();
    }
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
