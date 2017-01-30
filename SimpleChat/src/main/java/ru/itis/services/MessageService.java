package ru.itis.services;

import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

import java.util.List;

public interface MessageService extends BaseService <Message> {
    List<Message> findAllByChatId(Integer chatId);
    List<MessageDto> getNewMessages();
    void handleMessage(MessageDto inputMessage);
    List<Message> findNewMessages(Integer chatId, Integer userId);
    Integer saveLastMessage(Integer chatId, Integer userId, Integer messageId);
}
