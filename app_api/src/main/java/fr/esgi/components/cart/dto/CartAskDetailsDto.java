package fr.esgi.components.cart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartAskDetailsDto {
    @NotNull(message = "error.cartId.notnull")
    Integer cartId;
    @NotNull(message = "error.latitude.notnull")
    private Double latitude;
    @NotNull(message = "error.longitude.notnull")
    private Double longitude;
    @NotNull(message = "error.perimeter.notnull")
    private Double perimeter;
}
