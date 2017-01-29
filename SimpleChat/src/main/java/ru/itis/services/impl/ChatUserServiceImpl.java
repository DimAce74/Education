package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.converters.ChatUserToChatUserDtoConverter;
import ru.itis.dao.ChatUserDao;
import ru.itis.dto.ChatUserDto;
import ru.itis.dto.UserDataForRegistrationDto;
import ru.itis.exceptions.UserSigningException;
import ru.itis.models.ChatUser;
import ru.itis.security.utils.TokenGenerator;
import ru.itis.services.ChatUserService;

import java.util.List;

@Service("ChatUserService")
@Transactional
public class ChatUserServiceImpl implements ChatUserService {
    @Autowired
    private ChatUserDao chatUserDao;
    @Autowired
    private TokenGenerator generator;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(readOnly = true)
    public ChatUser find(int id) {
        return chatUserDao.find(id);
    }

    @Override
    public int save(ChatUser chatUser) {
        return chatUserDao.save(chatUser);
    }

    @Override
    public void delete(int id) {
        chatUserDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatUser> findAll() {
        return chatUserDao.findAll();
    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        chatUserDao.saveUserToChat(userId, chatId);
    }

    @Override
    public ChatUserDto registerUser(UserDataForRegistrationDto userDataForRegistrationDto) {

        ChatUser newUser = new ChatUser.Builder()
                .login(userDataForRegistrationDto.getLogin())
                .name(userDataForRegistrationDto.getName())
                .password(encoder.encode(userDataForRegistrationDto.getPassword()))
                .build();
        int userId = chatUserDao.save(newUser);
        ChatUser savedUser = chatUserDao.find(userId);
        return ChatUserToChatUserDtoConverter.convertWithoutChat(savedUser);
    }

    @Override
    public boolean isLoginExists(String login) {
        return chatUserDao.isLoginExists(login);
    }

    @Override
    public ChatUser login(String password, String login) {
        ChatUser chatUser = chatUserDao.findByLogin(login);
        if (encoder.matches(password, chatUser.getPassword())) {
            return chatUser;
        } else {
            throw new UserSigningException();
        }
    }

    @Override
    public boolean isExistsToken(String token) {
        return chatUserDao.isExistsToken(token);
    }

    @Override
    public ChatUser findUserByToken(String token) {
        return chatUserDao.findUserByToken(token);
    }

    @Override
    public String getToken(ChatUser chatUser) {
        String token = generator.generateToken();
        chatUser.getTokens().add(token);
        chatUserDao.save(chatUser);
        return token;
    }

    @Override
    public void logout(String token) {
        ChatUser chatUser = findUserByToken(token);
        chatUser.getTokens().remove(token);
        save(chatUser);
    }
}