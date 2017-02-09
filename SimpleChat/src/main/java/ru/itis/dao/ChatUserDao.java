package ru.itis.dao;

import ru.itis.models.ChatUser;


public interface ChatUserDao extends Dao<ChatUser> {
    void saveUserToChat(Integer userId, Integer chatId);
    boolean isLoginExists(String login);
    ChatUser findByLogin(String login);
    boolean isExistsToken(String token);
    ChatUser findUserByToken (String token);
    boolean isMemberOfChat(Integer userId, Integer chatId);
}
