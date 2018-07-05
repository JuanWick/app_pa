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
public class ProductCompletDto {
    private Integer id;
    @NotNull (message = "error.name.notnull")
    @Size(min = 1, max = 255, message = "error.name.size")
    private String name;
    @Size(min = 1, max = 255, message = "error.info.size")
    private String info;
    @Size(min = 1, max = 255, message = "error.barreCode.size")
    private String barreCode;
    @NotNull (message = "error.price.notnull")
    @Size(min = 1, max = 10, message = "error.price.size")
    private Double price;
}
