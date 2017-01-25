package ru.itis.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatSessionDao;
import ru.itis.models.ChatSession;

import java.util.List;

@Transactional
@Repository
public class ChatSessionDaoHibernateImpl implements ChatSessionDao{

    private SessionFactory sessionFactory;
    @Autowired
    public ChatSessionDaoHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public ChatSession find(int id) {
        //language=HQL
        return getSession().createQuery("from ChatSession where id = ?", ChatSession.class)
                .setParameter(0, id).getSingleResult();
    }

    @Override
    public int save(ChatSession chatSession) {
        getSession().saveOrUpdate(chatSession);
        return chatSession.getId();    }

    @Override
    public void delete(int id) {
        Session session = getSession();
        ChatSession chatSession = find(id);
        session.delete(chatSession);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatSession> findAll() {
        //language=HQL
        return getSession().createQuery("from ChatSession", ChatSession.class).list();    }

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
