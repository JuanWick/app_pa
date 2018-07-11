package fr.esgi.components.cart.adapter;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.components.cart.dto.CartDto;
import fr.esgi.components.product.dto.ProductCompletDto;
import fr.esgi.components.product.dto.ProductDto;
import fr.esgi.components.cart.dto.SharedDto;

import java.util.ArrayList;
import java.util.List;

public class CartApiAdapter {

    public Cart convertToModel (CartDto cartDto){
        Cart cart = new Cart();

        User user = new User();
        user.setId(cartDto.getUserId());
        cart.setUser(user);

        List<Product> products = new ArrayList<>();
        if(null != cartDto.getProducts()){
            for(ProductCompletDto productDto: cartDto.getProducts()){
                Product product = Product.builder().id(productDto.getId()).build();
                products.add(product);
            }
        }
        cart.setProducts(products);

        List<User> sharedusers = new ArrayList<>();
        if(null != cartDto.getSharedusers()){
            for(SharedDto sharedDto: cartDto.getSharedusers()){
                User shareduser = new User();
                shareduser.setId(sharedDto.getUserId());
                sharedusers.add(shareduser);
            }
        }
        cart.setSharedUsers(sharedusers);

        return cart;
    }

    public CartDto convertToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setUserId(cart.getUser().getId());

        List<ProductCompletDto> productsDto = new ArrayList<>();
        if(null != cart.getProducts()){
            for(Product product:cart.getProducts()){
                ProductCompletDto productDto = ProductCompletDto.builder()
                        .barreCode(product.getBarreCode())
                .price(product.getPrice())
                .barreCode(product.getBarreCode())
                .id(product.getId())
                .info(product.getInfo())
                .name(product.getName())
                .build();
                productsDto.add(productDto);
            }
        }
        cartDto.setProducts(productsDto);

        List<SharedDto> sharedusers = new ArrayList<>();
        if(null != cart.getSharedUsers()){
            for(User user:cart.getSharedUsers()){
                SharedDto sharedDto = new SharedDto();
                sharedDto.setUserId(user.getId());
                sharedusers.add(sharedDto);
            }
        }
        cartDto.setProducts(productsDto);
        cartDto.setSharedusers(sharedusers);
       return cartDto;
    }
}
