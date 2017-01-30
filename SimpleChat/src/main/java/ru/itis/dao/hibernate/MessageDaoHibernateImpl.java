package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.dao.MessageDao;
import ru.itis.exceptions.SavingMessageException;
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
        Integer messageId =(Integer) getSession().createNativeQuery("SELECT * FROM chat_member WHERE chat_id=? AND user_id=?")
                .setParameter(1, chatId)
                .setParameter(2, userId)
                .addScalar("last_message_id", IntegerType.INSTANCE)
                .getSingleResult();
        if(messageId!=null){
            Message lastMessage = find(messageId);
            return getSession().createQuery("from Message where chat.id=:chatId and id > :messageId", Message.class)
                    .setParameter("chatId", chatId)
                    .setParameter("messageId", lastMessage.getId()).list();
        }else{
            return findAllByChatId(chatId);
        }
    }

    @Override
    public Integer saveLastMessage(Integer chatId, Integer userId, Integer messageId) {
        int row = getSession().createNativeQuery("UPDATE chat_member SET last_message_id=? WHERE chat_id=? AND user_id=?")
                .setParameter(1, messageId)
                .setParameter(2, chatId)
                .setParameter(3, userId).executeUpdate();
        if(row==1){
            return messageId;
        }else {
            throw new SavingMessageException();
        }
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
