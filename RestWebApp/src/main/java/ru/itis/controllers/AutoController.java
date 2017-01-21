package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Auto;
import ru.itis.models.User;
import ru.itis.services.AutoService;
import ru.itis.services.UsersService;

import java.util.List;

@Controller
public class AutoController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AutoService autoService;


    @GetMapping(value = "/users/{userId}/autos")
    @ResponseBody ModelAndView showUserAuto(
            @PathVariable("userId") int userId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = usersService.findUser(userId);
        List<Auto> autoList = user.getListAuto();
        modelAndView.addObject("autos", autoList);
        modelAndView.addObject("user", user);
        modelAndView.addObject("auto", new Auto());
        modelAndView.setViewName("showAuto");
        return modelAndView;
    }

    @PostMapping(value = "/users/{userId}/autos")
    @ResponseBody ModelAndView addAuto(
            @ModelAttribute("auto") Auto auto,
            @PathVariable("userId") int userId)  {
        ModelAndView modelAndView = new ModelAndView();
        User user = usersService.findUser(userId);
        auto.setUser(user);
        autoService.addAuto(auto);

        List<Auto> autoList = user.getListAuto();
        modelAndView.addObject("autos", autoList);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("showAuto");
        return modelAndView;
    }

    @GetMapping(value = "/autos")
    @ResponseBody ModelAndView showAllAuto(){
        ModelAndView modelAndView = new ModelAndView();
        List<Auto> autoList = autoService.showAllAuto();
        modelAndView.addObject("autos", autoList);
        modelAndView.setViewName("showAuto");
        return modelAndView;
    }

    @PutMapping(value = "/users/{userId}/autos/{autoId}")
    public String updateAuto(
            @PathVariable("userId") int userId,
            @PathVariable("autoId") int autoId,
            @RequestParam("model") String model,
            @RequestParam("color") String color){
        Auto auto = autoService.findAuto(autoId);
        auto.setModel(model);
        auto.setColor(color);
        autoService.updateAuto(auto);
        return "redirect:/users/"+userId+"/autos";
    }

    @PostMapping(value = "/users/{userId}/autos/{autoId}")
    @ResponseBody ModelAndView showUpdatePage(
            @PathVariable("autoId") int autoId){
        ModelAndView modelAndView = new ModelAndView();
        Auto auto = autoService.findAuto(autoId);
        modelAndView.addObject("auto", auto);
        modelAndView.setViewName("updateAuto");
        return modelAndView;
    }

    @DeleteMapping(value = "/users/{userId}/autos/{autoId}")
    public String deleteAuto(
            @PathVariable("userId") int userId,
            @PathVariable("autoId") int autoId) {
        autoService.deleteAuto(autoId);
        return "redirect:/users/"+userId+"/autos";
    }

}
