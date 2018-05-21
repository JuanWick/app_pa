package fr.esgi.components.user.adapter;

import entities.User;
import fr.esgi.components.user.dto.UserDto;

public class UserApiAdapter {
    public User convertToModel (UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setName(userDto.getName());

        return user;
    }

    public UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setName(user.getName());
        return userDto;
    }
}
