package ru.itis.services;

import ru.itis.dto.UserDataForRegistrationDto;
import ru.itis.models.ChatUser;

public interface ChatUserService extends BaseService <ChatUser> {
    void saveUserToChat(Integer userId, Integer chatId);
    void registerUser(UserDataForRegistrationDto userDataForRegistrationDto);
    boolean isLoginExists(String login);
    ChatUser login(String password, String login);
    boolean isExistsToken (String token);
    ChatUser findUserByToken (String token);
    String getToken(ChatUser chatUser);

    void logout(String token);

    boolean isMemberOfChat(Integer userId, Integer chatId);
}
