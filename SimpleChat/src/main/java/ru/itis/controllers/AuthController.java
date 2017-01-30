package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDataForRegistrationDto;
import ru.itis.exceptions.UserSigningException;
import ru.itis.models.ChatUser;
import ru.itis.services.ChatUserService;

@RestController
public class AuthController {

    @Autowired
    private ChatUserService chatUserService;

    @PostMapping("/newuser")
    public ResponseEntity signUp(@RequestBody UserDataForRegistrationDto user) {
        if (chatUserService.isLoginExists(user.getLogin())){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }else{
            chatUserService.registerUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("password") String password,
                                        @RequestHeader("login") String login) {
        if (chatUserService.isLoginExists(login)) {

            try {
                ChatUser chatUser = chatUserService.login(password, login);
                String token = chatUserService.getToken(chatUser);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Auth-Token", token);
                return new ResponseEntity<>(null, headers, HttpStatus.OK);
            } catch (UserSigningException e){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/signout")
    public ResponseEntity logout(@RequestHeader("Auth-Token") String token){
        chatUserService.logout(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
