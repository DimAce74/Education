package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.itis.converters.MessageToMessageDtoConverter;
import ru.itis.dto.MessageDto;
import ru.itis.models.ChatUser;
import ru.itis.models.Message;
import ru.itis.services.ChatService;
import ru.itis.services.ChatUserService;
import ru.itis.services.MessageService;

import java.util.ArrayList;
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
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/chats/{chatId}/messages")
    public void sendMessage(@RequestBody MessageDto messageDto,
                            @PathVariable("chatId") int chatId,
                            @RequestHeader("Auth-Token") String token) {
        ChatUser chatUser = chatUserService.findUserByToken(token);
        Message message = new Message.Builder()
                .chat(chatService.find(chatId))
                .chatUser(chatUser)
                .text(messageDto.getText())
                .build();
        int messageId=messageService.save(message);
        messageService.saveLastMessage(chatId, chatUser.getId(), messageId);
        messageDto = MessageToMessageDtoConverter.convertWithoutChatWithChatUserName(message);
        template.convertAndSend("/topic/"+chatId+"/messages", messageDto);
    }

    @GetMapping("/chats/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(
            @PathVariable("chatId") int chatId,
            @RequestHeader("Auth-Token") String token,
            @RequestParam("get") String choice){
        int userId = chatUserService.findUserByToken(token).getId();
        if(choice.equals("all")) {
            List<Message> messageList = messageService.findAllByChatId(chatId);
            List<MessageDto> result =messageList.stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .sorted(Comparator.comparing(MessageDto::getId))
                    .collect(Collectors.toList());
            if(!messageList.isEmpty()) {
                Message lastMessage = messageList.stream()
                        .max(Comparator.comparing(Message::getId)).get();
                messageService.saveLastMessage(chatId, userId, lastMessage.getId());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else if(choice.equals("new")){
            List<MessageDto> result = messageService.findNewMessages(chatId, userId).stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .sorted(Comparator.comparing(MessageDto::getId))
                    .collect(Collectors.toList());
            if(result.size()>10){
                List<MessageDto> lastResult = new ArrayList<>();
                for (int i = result.size()-1; i>result.size()-11; i--){
                    lastResult.add(result.get(i));
                }
                lastResult.sort(Comparator.comparing(MessageDto::getId));
                result = lastResult;
            }
            if(!result.isEmpty()) {
                MessageDto lastMessageDto = result.stream()
                        .max(Comparator.comparing(MessageDto::getId)).get();
                messageService.saveLastMessage(chatId, userId, lastMessageDto.getId());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else if(choice.equals("last")){
            List<MessageDto> result = messageService.findAllByChatId(chatId).stream()
                    .map(MessageToMessageDtoConverter::convertWithoutChatWithChatUserName)
                    .sorted(Comparator.comparing(MessageDto::getId))
                    .collect(Collectors.toList());
            if(result.size()>10){
                List<MessageDto> lastResult = new ArrayList<>();
                for (int i = result.size()-1; i>result.size()-11; i--){
                    lastResult.add(result.get(i));
                }
                lastResult.sort(Comparator.comparing(MessageDto::getId));
                result = lastResult;
            }
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
