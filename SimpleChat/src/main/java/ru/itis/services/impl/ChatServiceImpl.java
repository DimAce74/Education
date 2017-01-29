package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.ChatDao;
import ru.itis.models.Chat;
import ru.itis.services.ChatService;

import java.util.List;

@Service("ChatService")
@Transactional//(propagation = Propagation.REQUIRES_NEW)
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    @Transactional(readOnly = true)
    public Chat find(int id) {

        return chatDao.find(id);
    }

    @Override
    public int save(Chat chat) {
        return chatDao.save(chat);
    }

    @Override
    public void delete(int id) {
        chatDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        return chatDao.findAll();
    }
}
