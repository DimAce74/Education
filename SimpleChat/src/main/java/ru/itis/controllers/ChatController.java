package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.ChatToChatDtoConverter;
import ru.itis.dto.ChatDto;
import ru.itis.dto.MessageDto;
import ru.itis.models.Chat;
import ru.itis.services.ChatService;
import ru.itis.services.ChatUserService;
import ru.itis.services.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatUserService chatUserService;

    @PostMapping("/chats")
    public ResponseEntity<Integer> createChat(
            @RequestBody String chatName){
        Chat chat = new Chat.Builder()
                .name(chatName)
                .build();
        return new ResponseEntity<>(chatService.save(chat), HttpStatus.CREATED);
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatDto>> getChats(){
        List<Chat> chats = chatService.findAll();
        List<ChatDto> chatDtoList = chats.stream().map(ChatToChatDtoConverter::convertWithoutChatUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(chatDtoList, HttpStatus.OK);
    }

    @PostMapping("/chats/{chatId}/members/{userId}")
    public ResponseEntity addUserToChat(
            @PathVariable("chatId") int chatId,
            @PathVariable("userId") int userId){
        chatUserService.saveUserToChat(userId, chatId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
