package fr.esgi.services.carts;

import entities.Cart;
import entities.Store;
import entities.User;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.reporitories.users.services.UserData;

public interface CartService {
    Cart save(CartData cartData, Cart cart);
    Cart createCart(CartData cartData, Integer userId);
}
