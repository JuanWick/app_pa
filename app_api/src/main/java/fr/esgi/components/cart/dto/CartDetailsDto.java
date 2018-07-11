package fr.esgi.components.cart.dto;

import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductCompletWithSearchResultDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDetailsDto {
    private Integer cartId;
    private List<ProductCompletWithSearchResultDto> products;
    private List<SharedDto> sharedusers;
}
