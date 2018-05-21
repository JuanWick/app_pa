package fr.esgi.components.product.adapter;

import entities.Product;
import fr.esgi.components.product.dto.ProductCompletDto;

public class ProductApiAdapter {
    public Product convertToModel (ProductCompletDto productCompletDto){
       return Product.builder()
                .barreCode(productCompletDto.getBarreCode())
                .info(productCompletDto.getInfo())
                .name(productCompletDto.getName())
                .id(null != productCompletDto.getId()?productCompletDto.getId():null)
                .build();
    }

    public ProductCompletDto convertToDto(Product product){
        return ProductCompletDto.builder()
                .id(product.getId())
                .info(product.getInfo())
                .name(product.getName())
                .barreCode(product.getBarreCode())
                .build();
    }
}
