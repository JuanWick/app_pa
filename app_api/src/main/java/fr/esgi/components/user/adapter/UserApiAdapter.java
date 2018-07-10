package fr.esgi.components.user.adapter;

import entities.User;
import entities.UserAuthenticator;
import fr.esgi.components.user.dto.UserDetailsDto;
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
        return UserDto.builder()
            .firstName(user.getFirstName())
            .name(user.getName())
            .id(user.getId())
            .build();
    }

    public UserDetailsDto convertToUserDetailsDto(User user, UserAuthenticator userAuthenticator){
        return UserDetailsDto.builder()
                .firstName(user.getFirstName())
                .name(user.getName())
                .id(user.getId())
                .mail(userAuthenticator.getEmail())
                .username(userAuthenticator.getLogin())
                .build();
    }
}
