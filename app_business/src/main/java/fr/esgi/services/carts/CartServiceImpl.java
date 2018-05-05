package fr.esgi.services.carts;

import entities.Cart;
import entities.Store;
import entities.User;
import fr.esgi.reporitories.carts.services.CartData;
import fr.esgi.reporitories.users.services.UserData;

public class CartServiceImpl implements CartService {

    @Override
    public Cart save(CartData cartData, Cart cart) {
        return cartData.save(cart);
    }

    @Override
    public Cart createCart(CartData cartData, Integer userId) {
        User user = new User();
        user.setId(userId);

        Cart cart = new Cart();
        cart.setUser(user);
        System.out.println("Avant save : "+cart.toString());
        return cartData.save(cart);
    }
}
