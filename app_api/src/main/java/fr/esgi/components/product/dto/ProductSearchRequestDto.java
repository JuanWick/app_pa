package fr.esgi.components.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
