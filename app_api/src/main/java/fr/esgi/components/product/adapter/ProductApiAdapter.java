package fr.esgi.components.product.adapter;

import entities.Product;
import entities.Store;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductDto;
import fr.esgi.components.product.dto.ProductSearchResultDto;
import fr.esgi.components.store.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;

public class ProductApiAdapter {
    public Product convertToModel (ProductCompletDto productCompletDto){
       return Product.builder()
                .barreCode(productCompletDto.getBarreCode())
                .info(productCompletDto.getInfo())
                .name(productCompletDto.getName())
                .id(productCompletDto.getId())
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

    public ProductSearchResultDto convertListToProductSearchResultDto(List<Store> search) {
        List<StoreDto> storeDtos = new ArrayList<>();

        for(Store store : search){
            storeDtos.add(StoreDto.builder()
            .id(store.getId())
            .address(store.getAddress())
            .city(store.getCity())
            .country(store.getCountry())
            .latitude(store.getLatitude())
            .longitude(store.getLongitude())
            .name(store.getName())
            .distance(store.getDistance()).build());
        }

        return ProductSearchResultDto.builder().stores(storeDtos).build();
    }
}
