package fr.esgi.reporitories.carts.services;

import entities.Cart;
import fr.esgi.reporitories.carts.CartRepository;
import fr.esgi.reporitories.carts.adapter.CartDataAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class HibernateCartData implements CartData {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartDataAdapter cartDataAdapter;

    public HibernateCartData() {}

    @Override
    public Cart getById(int id) {
        if(cartRepository.findById(id).isPresent()){
            TCartEntity  tCartEntity = cartRepository.findById(id).get();
            return cartDataAdapter.entityToModel(tCartEntity, true);
        } else {
            return null;
        }
    }

    @Override
    public Cart save(Cart cart) {
        TCartEntity cartEntity = cartDataAdapter.modelToEntity(cart, true);
        TUserEntity userEntity = userRepository.findById(cart.getUser().getId()).get();
        cartEntity.setUser(userEntity);
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
    public boolean deleteProduct(Integer cartId, Integer productId) {
        boolean result = false;
        if(cartRepository.findById(cartId).isPresent()){
            TCartEntity  tCartEntity = cartRepository.findById(cartId).get();
            if(null != tCartEntity.getProducts()){
                List<TProductEntity> updatedProductList = new ArrayList<>();
                for(TProductEntity productEntity:tCartEntity.getProducts()){
                    if(productEntity.getProductId() != productId){
                        updatedProductList.add(productEntity);
                    } else {
                        result = true;
                    }
                }
                tCartEntity.setProducts(updatedProductList);
                cartRepository.save(tCartEntity);
            }
        }
        return result;
    }


}
