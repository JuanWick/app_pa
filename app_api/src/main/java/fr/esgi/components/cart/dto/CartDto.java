package fr.esgi.components.cart.dto;

import entities.Product;
import entities.User;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Integer userId;
    private List<ProductDto> products;
    private List<SharedDto> sharedusers;
}
