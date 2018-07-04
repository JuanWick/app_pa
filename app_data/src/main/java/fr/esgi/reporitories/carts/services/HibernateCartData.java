package fr.esgi.reporitories.carts.services;

import entities.Cart;
import fr.esgi.reporitories.carts.CartRepository;
import fr.esgi.reporitories.carts.adapter.CartDataAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class HibernateCartData implements CartData {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDataAdapter cartDataAdapter;

    public HibernateCartData() {}

    @Override
    public Cart getById(int id) {
        Cart cart = null;
        if(cartRepository.findById(id).isPresent()){
            TCartEntity  tCartEntity = cartRepository.findById(id).get();
            cart = cartDataAdapter.entityToModel(tCartEntity, true);
        }
        return cart;
    }

    @Override
    public Cart save(Cart cart) {
        TCartEntity cartEntity = cartDataAdapter.modelToEntity(cart, true);
        cartEntity = cartRepository.save(cartEntity);
        return cartDataAdapter.entityToModel(cartEntity, true);
    }

    @Override
    public void delete(int id) {
        if(cartRepository.findById(id).isPresent()){
            TCartEntity cartEntity = cartRepository.findById(id).get();
            cartRepository.delete(cartEntity);
        }
    }

    @Override
    public void deleteProduct(Integer cartId, Integer productId) {
        if(cartRepository.findById(cartId).isPresent()){
            TCartEntity  tCartEntity = cartRepository.findById(cartId).get();
            if(null != tCartEntity.getProducts()){
                List<TProductEntity> updatedProductList = new ArrayList<>();
                for(TProductEntity productEntity:tCartEntity.getProducts()){
                    if(productEntity.getId() != productId){
                        updatedProductList.add(productEntity);
                    }
                }
                tCartEntity.setProducts(updatedProductList);
                cartRepository.save(tCartEntity);
            }
        }
    }


}
