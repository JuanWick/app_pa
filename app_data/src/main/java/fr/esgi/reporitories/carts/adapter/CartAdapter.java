package fr.esgi.reporitories.carts.adapter;

import entities.Cart;
import entities.User;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class CartAdapter {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    CartProductAdapter cartProductAdapter;

    @Autowired
    SharedCartAdapter sharedCartAdapter;

    public Cart convertToModel(TCartEntity cartEntity){
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setUser(userAdapter.adapt(cartEntity.gettUserByUserId()));
        if(null != cartEntity.getrCartProductsById()){
            cart.setProducts(cartProductAdapter.adapt(cartEntity.getrCartProductsById()));
        }
        if(null != cartEntity.getrSharedCartsById()){
            cart.setSharedUsers(sharedCartAdapter.adapt(cartEntity.getrSharedCartsById()));
        }
        return cart;
    }

    public TCartEntity convertToEntity(Cart cart){
        TCartEntity cartEntity = new TCartEntity();
        if(null != cart.getId()){
            cartEntity.setId(cart.getId());
        }
        cartEntity.settUserByUserId(userAdapter.convert(cart.getUser()));
        if(null != cart.getProducts()){
            cartEntity.setrCartProductsById(cartProductAdapter.convert(cart));
        }
        if(null != cart.getSharedUsers()){
            cartEntity.setrSharedCartsById(sharedCartAdapter.convert(cart));
        }
        return cartEntity;
    }
}
