package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.MessageToMessageDtoConverter;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.services.ChatService;
import ru.itis.services.ChatUserService;
import ru.itis.services.MessageService;

import java.util.Comparator;
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

    @PostMapping("/chats/{chatId}/messages/{userId}")
    public void sendMessage(@RequestBody MessageDto messageDto,
                            @PathVariable("chatId") int chatId,
                            @PathVariable("userId") int userId) {
        Message message = new Message.Builder()
                .chat(chatService.find(chatId))
                .chatUser(chatUserService.find(userId))
                .text(messageDto.getText())
                .build();
        int messageId=messageService.save(message);
        messageService.saveLastMessage(chatId, userId, messageId);
        messageService.handleMessage(messageDto);
    }

    @GetMapping("/chats/{chatId}/messages/{userId}")
    public ResponseEntity<List<MessageDto>> getMessages(
            @PathVariable("chatId") int chatId,
            @PathVariable("userId") int userId,
            @RequestParam("get") String choice){
        if(choice.equals("all")) {
            List<Message> messageList = messageService.findAllByChatId(chatId);
            List<MessageDto> result =messageList.stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .collect(Collectors.toList());
            if(!messageList.isEmpty()) {
                Message lastMessage = messageList.stream()
                        .max(Comparator.comparing(Message::getId)).get();
                messageService.saveLastMessage(chatId, userId, lastMessage.getId());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
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
                List<MessageDto> result = messageService.getNewMessages().stream()
                        .filter(messageDto -> messageDto.getChatDto().getId() == chatId)
                        .collect(Collectors.toList());
                MessageDto lastMessageDto = result.stream()
                        .max(Comparator.comparing(MessageDto::getId)).get();
                messageService.saveLastMessage(chatId, userId, lastMessageDto.getId());
                messageService.getNewMessages().clear();
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }else if(choice.equals("new")){
            List<MessageDto> result = messageService.findNewMessages(chatId, userId).stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .collect(Collectors.toList());
            if(!result.isEmpty()) {
                MessageDto lastMessageDto = result.stream()
                        .max(Comparator.comparing(MessageDto::getId)).get();
                messageService.saveLastMessage(chatId, userId, lastMessageDto.getId());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
