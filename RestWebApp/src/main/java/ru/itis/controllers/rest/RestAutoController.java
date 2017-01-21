package ru.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.converter.AutoToAutoDtoConverter;
import ru.itis.dto.AutoDto;
import ru.itis.models.Auto;
import ru.itis.services.AutoService;
import ru.itis.services.UsersService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class RestAutoController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private AutoService autoService;

    @GetMapping(value = "/users/{userId}/autos")
    public List<AutoDto> getAutos(
            @PathVariable("userId") int userId){
        List<AutoDto> result = new ArrayList<>();
        List<Auto> autoList = usersService.findUser(userId).getListAuto();
        for(Auto auto : autoList){
            result.add(AutoToAutoDtoConverter.convertWithUser(auto));
        }
        return result;
    }

    @PostMapping(value = "/users/{userId}/autos")
    public ResponseEntity<List<AutoDto>> addAuto(
            @RequestBody AutoDto autoDto,
            @PathVariable("userId") int userId){
        Auto auto = new Auto (autoDto.getModel(), autoDto.getColor(), usersService.findUser(userId));
        autoService.addAuto(auto);
        List<AutoDto> result = new ArrayList<>();
        List<Auto> autoList = usersService.findUser(userId).getListAuto();
        for(Auto currentAuto : autoList){
            result.add(AutoToAutoDtoConverter.convertWithUser(currentAuto));
        }
        return new ResponseEntity <List<AutoDto>>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{userId}/autos/{autoId}")
    public ResponseEntity<List<AutoDto>> updateAuto(
            @PathVariable("userId") int userId,
            @PathVariable("autoId") int autoId,
            @RequestParam("model") String model,
            @RequestParam("color") String color){
        Auto auto = autoService.findAuto(autoId);
        auto.setModel(model);
        auto.setColor(color);
        autoService.updateAuto(auto);
        List<AutoDto> result = new ArrayList<>();
        List<Auto> autoList = usersService.findUser(userId).getListAuto();
        for(Auto currentAuto : autoList){
            result.add(AutoToAutoDtoConverter.convertWithUser(currentAuto));
        }
        return new ResponseEntity <List<AutoDto>>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{userId}/autos/{autoId}")
    public ResponseEntity<List<AutoDto>> deleteAuto(
            @PathVariable("userId") int userId,
            @PathVariable("autoId") int autoId) {
        autoService.deleteAuto(autoId);
        List<AutoDto> result = new ArrayList<>();
        List<Auto> autoList = usersService.findUser(userId).getListAuto();
        for(Auto auto : autoList){
            result.add(AutoToAutoDtoConverter.convertWithUser(auto));
        }
        return new ResponseEntity <List<AutoDto>>(result, HttpStatus.OK);
    }

}
