package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;

import java.util.ArrayList;

public class ChatToChatDtoConverter {
    public static ChatDto convertWithoutChatUser(Chat chat){
        ChatDto chatDto = new ChatDto.Builder()
                .id(chat.getId())
                .name(chat.getName())
                .build();
        return chatDto;
    }

    public static ChatDto convertWithChatUser(Chat chat){
        ChatDto chatDto = new ChatDto.Builder()
                .id(chat.getId())
                .name(chat.getName())
                .chatUserDtoList(new ArrayList<ChatUserDto>())
                .build();

        for (ChatUser chatUser: chat.getChatUserList()){
            ChatUserDto chatUserDto = ChatUserToChatUserDtoConverter.convertWithoutChat(chatUser);
            chatDto.getChatUserDtoList().add(chatUserDto);
        }

        return chatDto;
    }
}
