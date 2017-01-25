package ru.itis.converters;

import ru.itis.dto.ChatSessionDto;
import ru.itis.dto.ChatUserDto;
import ru.itis.dto.MessageDto;
import ru.itis.models.ChatSession;

public class ChatSessionToChatSessionDtoConverter {

    public static ChatSessionDto convertWithoutChatUserWithoutMessage(ChatSession chatSession){
        return new ChatSessionDto.Builder()
                .id(chatSession.getId())
                .token(chatSession.getToken())
                .build();
    }

    public static ChatSessionDto convertWithChatUserWithMessage(ChatSession chatSession){
        ChatUserDto chatUserDto = ChatUserToChatUserDtoConverter.convertWithoutChat(chatSession.getChatUser());
        MessageDto messageDto = MessageToMessageDtoConverter.convertWithoutChatWithoutChatUser(chatSession.getLastMessage());
        return new ChatSessionDto.Builder()
                .id(chatSession.getId())
                .token(chatSession.getToken())
                .chatUserDto(chatUserDto)
                .lastMessageDto(messageDto)
                .build();
    }

}
