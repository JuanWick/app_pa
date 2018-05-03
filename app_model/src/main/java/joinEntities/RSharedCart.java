package joinEntities;

import entities.Cart;
import entities.User;

public class RSharedCart {
    private User user;
    private Cart cart;

    public RSharedCart(User user, Cart cart) {
        this.user = user;
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
