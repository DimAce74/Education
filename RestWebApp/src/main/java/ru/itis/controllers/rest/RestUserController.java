package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.converter.UserToUserDtoConverter;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class RestUserController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/users")
    public List<UserDto> getUsers() {
        List<UserDto> result = new ArrayList<>();

        List<User> users = usersService.findAllUsers();
        for (User user : users) {
            result.add(UserToUserDtoConverter.convertWithAuto(user));
        }
        return result;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<List<UserDto>> addUser(@RequestBody UserDto userDto) {
        usersService.addUser(userDto.getName(), userDto.getAge());

        List<UserDto> result = new ArrayList<>();

        List<User> users = usersService.findAllUsers();
        for (User currentUser : users) {
            result.add(UserToUserDtoConverter.convertWithAuto(currentUser));
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/users/{userId}")
    public ResponseEntity<List<UserDto>> deleteUser(
            @PathVariable("userId") int userId) {
        usersService.deleteUser(userId);

        List<UserDto> result = new ArrayList<>();

        List<User> users = usersService.findAllUsers();
        for (User currentUser : users) {
            result.add(UserToUserDtoConverter.convertWithAuto(currentUser));
        }

        return new ResponseEntity<List<UserDto>>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<List<UserDto>> updateUser(
            @PathVariable("userId") int userId,
            @RequestParam("user_name") String userName,
            @RequestParam("user_age") int userAge){
        User user = usersService.findUser(userId);
        user.setName(userName);
        user.setAge(userAge);
        usersService.updateUser(user);
        List<UserDto> result = new ArrayList<>();

        List<User> users = usersService.findAllUsers();
        for (User currentUser : users) {
            result.add(UserToUserDtoConverter.convertWithAuto(currentUser));
        }

        return new ResponseEntity<List<UserDto>>(result, HttpStatus.OK);
    }

}
