package ru.itis.converters;

import ru.itis.dto.ChatDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

public class MessageToMessageDtoConverter {
    public static MessageDto convertWithoutChatWithoutChatUser(Message message){
        MessageDto messageDto = new MessageDto.Builder()
                .id(message.getId())
                .text(message.getText())
                .build();
        return messageDto;
    }

    public static MessageDto convertWithChatWithChatUser(Message message){
        ChatDto chatDto = ChatToChatDtoConverter.convertWithoutChatUser(message.getChat());
        ChatUserDto chatUserDto = ChatUserToChatUserDtoConverter.convertWithoutChat(message.getChatUser());
        MessageDto messageDto = new MessageDto.Builder()
                .id(message.getId())
                .chat(chatDto)
                .chatUser(chatUserDto)
                .text(message.getText())
                .build();
        return messageDto;
    }


}
