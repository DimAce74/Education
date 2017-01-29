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

    public static MessageDto convertWithoutChatWithChatUserName(Message message){
        MessageDto messageDto = new MessageDto.Builder()
                .id(message.getId())
                .userName(message.getChatUser().getName())
                .text(message.getText())
                .build();
        return messageDto;
    }

    public static MessageDto convertWithChatWithChatUser(Message message){
        ChatDto chatDto = ChatToChatDtoConverter.convertWithoutChatUser(message.getChat());
        MessageDto messageDto = new MessageDto.Builder()
                .id(message.getId())
                .chat(chatDto)
                .userName(message.getChatUser().getName())
                .text(message.getText())
                .build();
        return messageDto;
    }


}
