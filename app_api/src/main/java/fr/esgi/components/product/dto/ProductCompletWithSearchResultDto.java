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
public class ProductCompletWithSearchResultDto {
    private Integer id;
    private String name;
    private String info;
    private String barreCode;
    private Double price;
    private ProductSearchResultDto searchResults;
}
