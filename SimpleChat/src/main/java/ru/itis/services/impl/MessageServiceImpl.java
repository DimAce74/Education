package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dao.MessageDao;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.services.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public Message find(int id) {
        return messageDao.find(id);
    }

    @Override
    public int save(Message message) {
        return messageDao.save(message);
    }

    @Override
    public void delete(int id) {
        messageDao.delete(id);
    }

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public List<Message> findAllByChatId(Integer chatId) {
        return messageDao.findAllByChatId(chatId);
    }

    @Override
    public List<Message> findNewMessages(Integer chatId, Integer userId) {
        return messageDao.findNewMessages(chatId, userId);
    }

    @Override
    public Integer saveLastMessage(Integer chatId, Integer userId, Integer messageId) {
        return messageDao.saveLastMessage(chatId, userId, messageId);
    }
}
