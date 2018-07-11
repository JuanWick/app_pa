package fr.esgi.components.cart.dto;

import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Integer userId;
    private List<ProductCompletDto> products;
    private List<SharedDto> sharedusers;
}
