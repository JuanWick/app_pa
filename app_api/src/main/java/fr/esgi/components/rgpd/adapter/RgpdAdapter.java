package fr.esgi.components.rgpd.adapter;

import entities.Cart;
import entities.Product;
import entities.Store;
import entities.User;
import fr.esgi.components.rgpd.dto.*;

import java.util.ArrayList;
import java.util.List;

public class RgpdAdapter {
    public List<ProductCompletRgpdDto> productsToDtos(List<Product> products) {
        List<ProductCompletRgpdDto> productCompletRgpdDtos = new ArrayList<>();

        for(Product product : products){
            productCompletRgpdDtos.add(productToDto(product));
        }

        return productCompletRgpdDtos;
    }

    public ProductCompletRgpdDto productToDto(Product product){
        return ProductCompletRgpdDto.builder()
                .barreCode(product.getBarreCode())
                .info(product.getInfo())
                .name(product.getName())
                .build();
    }

    public List<SharedRgpdDto> sharedsUsersToDtod(List<User> sharedUsers) {
        List<SharedRgpdDto> sharedRgpdDtos = new ArrayList<>();

        for(User user : sharedUsers){
            sharedRgpdDtos.add(userToDto(user));
        }

        return sharedRgpdDtos;
    }

    private SharedRgpdDto userToDto(User user) {
        SharedRgpdDto sharedRgpdDto = new SharedRgpdDto();
        sharedRgpdDto.setSharedUser(UserRgpdDto.builder()
                .firstName(user.getFirstName())
                .name(user.getName())
                .build());
        return sharedRgpdDto;
    }

    public List<CartRgpdDto> cartsToDto(List<Cart> ownedCarts) {
        List<CartRgpdDto> cartRgpdDtos = new ArrayList<>();
        for (Cart cart:ownedCarts){
            cartRgpdDtos.add(cartToDto(cart));
        }
        return cartRgpdDtos;
    }

    private CartRgpdDto cartToDto(Cart cart) {
        CartRgpdDto cartRgpdDto = new CartRgpdDto();
        cartRgpdDto.setSharedusers(sharedsUsersToDtod(cart.getSharedUsers()));
        cartRgpdDto.setProducts(productsToDtos(cart.getProducts()));
        return cartRgpdDto;
    }

    public List<StoreRgpdDto> storesToDtos(List<Store> byUserId) {
        List<StoreRgpdDto> storeRgpdDto = new ArrayList<>();

        for(Store store : byUserId){
            storeRgpdDto.add(storeToDto(store));
        }

        return storeRgpdDto;

    }

    private StoreRgpdDto storeToDto(Store store) {
        return StoreRgpdDto.builder()
                .address(store.getAddress())
                .city(store.getCity())
                .country(store.getCountry())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .name(store.getName())
                .zipcode(store.getZipcode())
                .build();
    }
}
