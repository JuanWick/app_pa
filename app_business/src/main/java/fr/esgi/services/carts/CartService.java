package fr.esgi.services.carts;

import entities.Cart;
import entities.Product;
import fr.esgi.exception.CartNotFoundException;
import fr.esgi.exception.ProductNotFoundException;
import fr.esgi.exception.UserNotFoundException;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.products.services.ProductData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;
import fr.esgi.services.product.ProductService;

import java.util.List;
import java.util.Map;

public interface CartService {
    Cart save(CartData cartData, Cart cart);
    Cart getById(CartData cartData, Integer cartId) throws CartNotFoundException;
    Map<Product,  List<Object[]>> getByIdWithSearch(CartData cartData, StoreData storeData, ProductData productData, ProductService productService, Integer cartId, Double latitude, Double longitude, Double perimeter);
    Cart createCart(UserData userData, CartData cartData, Integer userId) throws UserNotFoundException;
    void delete(CartData cartData, Integer cartId) throws CartNotFoundException;
    Cart addProducts(CartData cartData, ProductData productData, Integer cartId, List<Integer> productsId) throws CartNotFoundException, ProductNotFoundException;
    void deleteProduct(CartData cartData, ProductData productData, Integer cartId, Integer productId) throws CartNotFoundException, ProductNotFoundException;
}
