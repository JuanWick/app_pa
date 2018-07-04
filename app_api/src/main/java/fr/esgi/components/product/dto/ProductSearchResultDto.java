package fr.esgi.components.product.dto;

import fr.esgi.components.store.dto.StoreDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class ProductSearchResultDto {
    private boolean error;
    private List<StoreDto> stores;
}
