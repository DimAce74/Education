package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;

import java.util.ArrayList;

public class ChatUserToChatUserDtoConverter {
    public static ChatUserDto convertWithoutChat (ChatUser chatUser){
        ChatUserDto chatUserDto = new ChatUserDto.Builder()
                .id(chatUser.getId())
                .login(chatUser.getLogin())
                .name(chatUser.getName())
                .password(chatUser.getPassword())
                .build();
        return chatUserDto;
    }

    public static ChatUserDto convertWithChat (ChatUser chatUser){
        ChatUserDto chatUserDto = new ChatUserDto.Builder()
                .id(chatUser.getId())
                .login(chatUser.getLogin())
                .password(chatUser.getPassword())
                .name(chatUser.getName())
                .chatDtoList(new ArrayList<ChatDto>())
                .build();
        for(Chat chat: chatUser.getChatList()){
            ChatDto chatDto = ChatToChatDtoConverter.convertWithoutChatUser(chat);
            chatUserDto.getChatDtoList().add(chatDto);
        }

        return chatUserDto;
    }
}
