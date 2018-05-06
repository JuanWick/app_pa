package fr.esgi.reporitories.carts.adapter;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.adapter.ProductAdapter;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.adapter.UserAdapter;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    ProductAdapter productAdapter;

    public Cart entityToModel(TCartEntity cartEntity, boolean all){
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setUser(userAdapter.entityToModel(cartEntity.getUser()));
        if(all && null != cartEntity.getProducts()){
            List<Product> products = new ArrayList<>();
            for(TProductEntity product:cartEntity.getProducts()){
                products.add(productAdapter.entityToModel(product, false));
            }
            cart.setProducts(products);
        }
        if(all && null != cartEntity.getSharedUsers()){
            List<User> users = new ArrayList<>();
            for(TUserEntity user:cartEntity.getSharedUsers()){

                users.add(userAdapter.entityToModel(user));
            }
            cart.setSharedUsers(users);
        }
        return cart;
    }

    public TCartEntity modelToEntity(Cart cart, boolean all){
        TCartEntity cartEntity = new TCartEntity();
        if(null != cart.getId()){
            cartEntity.setId(cart.getId());
        }
        cartEntity.setUser(userAdapter.modelToEntity(cart.getUser()));
        if(all && null != cart.getProducts()){
            List<TProductEntity> products = new ArrayList<>();
            for(Product product:cart.getProducts()){
                products.add(productAdapter.modelToEntity(product, false));
            }
            cartEntity.setProducts(products);
        }
        if(all && null != cart.getSharedUsers()){
            List<TUserEntity> users = new ArrayList<>();
            for(User user:cart.getSharedUsers()){
                users.add(userAdapter.modelToEntity(user));
            }
            cartEntity.setSharedUsers(users);
        }
        return cartEntity;
    }
}
