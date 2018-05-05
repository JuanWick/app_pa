package fr.esgi.components.user.adapter;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.components.cart.dto.ProductDto;
import fr.esgi.components.cart.dto.SharedDto;
import fr.esgi.components.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserDtoAdapter {
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
