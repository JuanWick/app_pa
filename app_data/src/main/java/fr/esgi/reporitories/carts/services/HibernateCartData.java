package fr.esgi.reporitories.carts.services;

import entities.Cart;
import fr.esgi.reporitories.carts.CartRepository;
import fr.esgi.reporitories.carts.adapter.CartAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public class HibernateCartData implements CartData {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartAdapter cartAdapter;

    public HibernateCartData() {}

    @Override
    public Cart getById(int id) {
        Cart cart = null;
        if(cartRepository.findById(id).isPresent()){
            TCartEntity  tCartEntity = cartRepository.findById(id).get();
            cart = cartAdapter.convertToModel(tCartEntity);
        }
        return cart;
    }

    @Override
    public Cart save(Cart cart) {
        TCartEntity cartEntity = cartAdapter.convertToEntity(cart);
        cartRepository.save(cartEntity);
        return cartAdapter.convertToModel(cartEntity);
    }


}
