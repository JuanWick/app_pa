package fr.esgi.services.carts;

import entities.Cart;
import entities.Product;
import fr.esgi.exception.CartNotFoundException;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.users.services.UserData;

import java.util.List;

public interface CartService {
    Cart save(CartData cartData, Cart cart);
    Cart getById(CartData cartData, Integer cartId) throws CartNotFoundException;
    Cart createCart(UserData userData, CartData cartData, Integer userId) throws UserNotFoundException;
    void delete(CartData cartData, Integer cartId) throws CartNotFoundException;
    Cart addProducts(CartData cartData, ProductData productData, Integer cartId, List<Integer> productsId) throws CartNotFoundException, ProductNotFoundException;
    void deleteProduct(CartData cartData, ProductData productData, Integer cartId, Integer productId) throws CartNotFoundException, ProductNotFoundException;
}
