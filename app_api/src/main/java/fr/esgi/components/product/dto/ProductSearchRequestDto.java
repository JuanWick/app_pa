package fr.esgi.components.product.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductSearchRequestDto {
    @NotNull(message = "error.searchValue.notnull")
    @Size(min = 1, max = 255, message = "error.searchValue.size")
    private String searchValue;
    @NotNull(message = "error.latitude.notnull")
    private Double latitude;
    @NotNull(message = "error.longitude.notnull")
    private Double longitude;
    @NotNull(message = "error.perimeter.notnull")
    private Double perimeter;
}
