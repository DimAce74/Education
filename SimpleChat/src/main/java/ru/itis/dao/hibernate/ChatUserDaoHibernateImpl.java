package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatUserDao;
import ru.itis.models.ChatUser;

import java.util.List;

@Transactional
@Repository
public class ChatUserDaoHibernateImpl implements ChatUserDao {

    private SessionFactory sessionFactory;
    @Autowired
    public ChatUserDaoHibernateImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public ChatUser find(int id) {
        //language=HQL
        return getSession().createQuery("from ChatUser where id = ?", ChatUser.class)
                .setParameter(0, id).getSingleResult();
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
    @Transactional(readOnly = true)
    public List<ChatUser> findAll() {
        //language=HQL
        return getSession().createQuery("from ChatUser", ChatUser.class).list();    }

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
