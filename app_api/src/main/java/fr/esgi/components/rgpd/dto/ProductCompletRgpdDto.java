package fr.esgi.components.rgpd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCompletRgpdDto {
    private String name;
    private String info;
    private String barreCode;
    private Double price;
}
