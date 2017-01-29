package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.MessageToMessageDtoConverter;
import ru.itis.dto.MessageDto;
import ru.itis.models.ChatUser;
import ru.itis.models.Message;
import ru.itis.services.ChatService;
import ru.itis.services.ChatUserService;
import ru.itis.services.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatUserService chatUserService;
/**
    @GetMapping("/chats/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessagesLongPooling(
            @PathVariable("chatId") int chatId) {
        synchronized (messageService.getNewMessages()) {
            while (messageService.getNewMessages().stream()
                    .filter(messageDto -> messageDto.getChatDto().getId() == chatId)
                    .count() == 0) {
                try {
                    messageService.getNewMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException();
                }
            }
            List<MessageDto> result = new ArrayList<>(messageService.getNewMessages().stream()
                    .filter(messageDto -> messageDto.getChatDto().getId() == chatId)
                    .collect(Collectors.toList()));
            messageService.getNewMessages().clear();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
*/
    @PostMapping("/chats/{chatId}/messages/{userId}")
    public void sendMessage(@RequestBody MessageDto messageDto,
                            @PathVariable("chatId") int chatId,
                            @PathVariable("userId") int userId) {
        Message message = new Message.Builder()
                .chat(chatService.find(chatId))
                .chatUser(chatUserService.find(userId))
                .text(messageDto.getText())
                .build();
        messageService.save(message);
        messageService.handleMessage(messageDto);
    }

    @GetMapping("/chats/{chatId}/messages/{userId}")
    public ResponseEntity<List<MessageDto>> getMessages(
            @PathVariable("chatId") int chatId,
            @PathVariable("userId") int userId,
            @RequestParam("get") String choice){
        if(choice.equals("all")) {
            return new ResponseEntity<>(messageService.findAllByChatId(chatId).stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }else if(choice.equals("online")){
            synchronized (messageService.getNewMessages()) {
                while (messageService.getNewMessages().stream()
                        .filter(messageDto -> messageDto.getChatDto().getId() == chatId)
                        .count() == 0) {
                    try {
                        messageService.getNewMessages().wait();
                    } catch (InterruptedException e) {
                        throw new IllegalArgumentException();
                    }
                }
                List<MessageDto> result = new ArrayList<>(messageService.getNewMessages().stream()
                        .filter(messageDto -> messageDto.getChatDto().getId() == chatId)
                        .collect(Collectors.toList()));
                messageService.getNewMessages().clear();
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }else if(choice.equals("new")){
            return new ResponseEntity<>(messageService.findNewMessages(chatId, userId).stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
