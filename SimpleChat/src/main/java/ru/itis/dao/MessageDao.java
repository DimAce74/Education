package ru.itis.dao;

import ru.itis.models.Message;

import java.util.List;

public interface MessageDao extends Dao<Message> {
    List<Message> findAllByChatId(Integer chatId);
    List<Message> findNewMessages(Integer chatId, Integer userId);
    Integer saveLastMessage(Integer chatId, Integer userId, Integer messageId);
}
