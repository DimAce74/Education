package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.dao.ChatUserDao;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;

import java.util.List;

@Repository
public class ChatUserDaoHibernateImpl implements ChatUserDao {

    private SessionFactory sessionFactory;
    @Autowired
    public ChatUserDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ChatUser find(int id) {
        return getSession().createQuery("from ChatUser where id = :id", ChatUser.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public int save(ChatUser chatUser) {
        getSession().saveOrUpdate(chatUser);
        return chatUser.getId();    }

    @Override
    public void delete(int id) {
        ChatUser chatUser = find(id);
        getSession().delete(chatUser);
    }

    @Override
    public List<ChatUser> findAll() {
        return getSession().createQuery("from ChatUser", ChatUser.class).list();    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        ChatUser chatUser = find(userId);
        Chat chat = getSession().createQuery("from Chat where id = :id", Chat.class)
                .setParameter("id", chatId).getSingleResult();
        chatUser.getChatList().add(chat);
        save(chatUser);
    }

    @Override
    public boolean isLoginExists(String login) {
        Query query = getSession().createQuery("from ChatUser u where u.login=:login");
        query.setParameter("login", login);
        return (query.uniqueResult()!=null);
    }

    @Override
    public ChatUser findByLogin(String login) {
        return getSession().createQuery("from ChatUser u where u.login = :login", ChatUser.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public boolean isExistsToken(String token) {
        return getSession().createQuery("from ChatUser u where :token in elements(u.tokens)")
                .setParameter("token",token).uniqueResult()!=null;
    }

    @Override
    public ChatUser findUserByToken(String token) {
        return getSession().createQuery("from ChatUser u where :token in elements(u.tokens)", ChatUser.class)
                .setParameter("token",token).getSingleResult();
    }




    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
