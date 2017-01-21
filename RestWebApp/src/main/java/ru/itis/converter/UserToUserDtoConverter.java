package ru.itis.converter;


import ru.itis.dto.AutoDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Auto;
import ru.itis.models.User;

import java.util.ArrayList;

public class UserToUserDtoConverter {

    public static UserDto convertWithoutAuto(User user){
        UserDto userDto = new UserDto.Builder().id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
        return userDto;
    }

    public static UserDto convertWithAuto(User user){
        UserDto userDto = new UserDto.Builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .listAutoDto(new ArrayList<AutoDto>())
                .build();

        for (Auto auto: user.getListAuto()) {
            AutoDto autoDto = AutoToAutoDtoConverter.convertWithoutUser(auto);
            userDto.getListAutoDto().add(autoDto);
        }


        return userDto;
    }
}
