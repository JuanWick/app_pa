package fr.esgi.reporitories.carts.services;

import entities.Cart;

import java.util.List;

public interface CartData {
    Cart getById(int id);
    List<Cart> getbyUserId(Integer userId);
    Cart save(Cart cart);
    void delete(int id);
    boolean deleteProduct(Integer cartId, Integer productId);
}
