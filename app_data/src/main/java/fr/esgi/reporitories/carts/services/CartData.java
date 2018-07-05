package fr.esgi.reporitories.carts.services;

import entities.Cart;

public interface CartData {
    Cart getById(int id);
    Cart save(Cart cart);
    void delete(int id);
    boolean deleteProduct(Integer cartId, Integer productId);
}
