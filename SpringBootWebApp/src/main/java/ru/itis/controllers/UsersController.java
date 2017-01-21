package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.List;


@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = {"/", "/index"})
    @ResponseBody ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/users")
    @ResponseBody ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = usersService.findAllUsers();
        modelAndView.addObject("users", userList);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("showUsers");
        return modelAndView;
    }

    @PostMapping(value = "/users")
    @ResponseBody ModelAndView addUser(@ModelAttribute("user") User user) {
        usersService.addUser(user.getName(), user.getAge());
        return getUsers();
    }

    @DeleteMapping(value="/users/{userId}")
    public String deleteUser(
            @PathVariable("userId") int userId){
        usersService.deleteUser(userId);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/{userId}")
    @ResponseBody ModelAndView showUpdatePage(
            @PathVariable("userId") int userId){
                ModelAndView modelAndView = new ModelAndView();
                User user=usersService.findUser(userId);
                modelAndView.addObject("user", user);
                modelAndView.setViewName("updateUser");
                return modelAndView;
    }

    @PutMapping(value = "/users/{userId}")
    public String updateUser(
            @PathVariable("userId") int userId,
            @RequestParam("user_name") String userName,
            @RequestParam("user_age") int userAge){
        User user = usersService.findUser(userId);
        user.setName(userName);
        user.setAge(userAge);
        usersService.updateUser(user);
        return "redirect:/users";
    }

}
