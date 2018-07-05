package fr.esgi.services.carts;

import entities.Cart;
import entities.Product;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.users.services.UserData;

import java.util.List;

public interface CartService {
    Cart save(CartData cartData, Cart cart);
    Cart createCart(UserData userData, CartData cartData, Integer userId) throws UserNotFoundException;
    void delete(CartData cartData, Integer cartId);
    Cart addProducts(CartData cartData, Integer cartId, List<Integer> productsId);
    void deleteProduct(CartData cartData,Integer cartId,Integer productId);
}
