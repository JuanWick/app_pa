package fr.esgi.components.product.adapter;

import entities.Product;
import entities.Store;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductDto;
import fr.esgi.components.product.dto.ProductSearchResultDto;
import fr.esgi.components.store.dto.StoreDto;
import fr.esgi.components.store.dto.StoreResultDto;

import java.util.ArrayList;
import java.util.List;

public class ProductApiAdapter {
    public Product convertToModel (ProductCompletDto productCompletDto){
       return Product.builder()
                .barreCode(productCompletDto.getBarreCode())
                .info(productCompletDto.getInfo())
                .name(productCompletDto.getName())
                .id(productCompletDto.getId())
                .price(productCompletDto.getPrice())
                .build();
    }

    public ProductCompletDto convertToDto(Product product){
        return ProductCompletDto.builder()
                .id(product.getId())
                .info(product.getInfo())
                .name(product.getName())
                .barreCode(product.getBarreCode())
                .price(product.getPrice())
                .build();
    }

    public ProductSearchResultDto convertListToProductSearchResultDto(List<Object[]> search) {
        List<StoreResultDto> storeResultDtos = new ArrayList<>();

        for(Object[] objects : search){
            Store store = (Store) objects[0];
            Product product = (Product) objects[1];
            storeResultDtos.add(StoreResultDto.builder()
            .id(store.getId())
            .address(store.getAddress())
            .city(store.getCity())
            .country(store.getCountry())
            .latitude(store.getLatitude())
            .longitude(store.getLongitude())
            .name(store.getName())
            .zipcode(store.getZipcode())
            .distance(store.getDistance())
            .productPrice(product.getPrice())
            .productId(product.getId())
            .build());
        }

        return ProductSearchResultDto.builder().stores(storeResultDtos).build();
    }
}
