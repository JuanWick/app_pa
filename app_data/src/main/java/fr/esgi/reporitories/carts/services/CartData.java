package fr.esgi.reporitories.carts.services;

import entities.Cart;
import entities.Product;
import entities.User;

import java.util.List;

public interface CartData {
    Cart getById(int id);
    Cart save(Cart cart);
    void delete(int id);
    void deleteProduct(Integer cartId, Integer productId);
}
