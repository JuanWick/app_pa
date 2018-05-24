package fr.esgi.components.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartProductsAddDto {
    private List<Integer> productsId;
}
