package fr.esgi.components.rgpd.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartRgpdDto {
    private List<ProductCompletRgpdDto> products;
    private List<SharedRgpdDto> sharedusers;
}
