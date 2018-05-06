package fr.esgi.reporitories.users.adapter;

import entities.Cart;
import entities.Product;
import entities.Role;
import entities.User;
import fr.esgi.reporitories.carts.adapter.CartAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {

    @Autowired
    CartAdapter cartAdapter;

    @Autowired
    RoleAdapter roleAdapter;

    public User entityToModel(TUserEntity userEntity, boolean all){
        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());

        if(all && null != userEntity.getCarts()){
            List<Cart> carts = new ArrayList<>();
            for(TCartEntity cart:userEntity.getCarts()){
                carts.add(cartAdapter.entityToModel(cart, false));
            }
            user.setCarts(carts);
        }

        if(all && null != userEntity.getRoles()){
            List<Role> roles = new ArrayList<>();
            for(TRoleEntity role:userEntity.getRoles()){
                roles.add(roleAdapter.entityToModel(role));
            }
            user.setRoles(roles);
        }
        return user;
    }

    public TUserEntity modelToEntity(User user, boolean all){
        TUserEntity userEntity = new TUserEntity();
        if(null != user.getId()){
            userEntity.setId(user.getId());
        }
        userEntity.setFirstName(user.getFirstName());
        userEntity.setName(user.getName());

        if(all && null != user.getCarts()){
            List<TCartEntity> carts = new ArrayList<>();
            for(Cart cart:user.getCarts()){
                carts.add(cartAdapter.modelToEntity(cart, false));
            }
            userEntity.setCarts(carts);
        }

        if(all && null != user.getRoles()){
            List<TRoleEntity> roles = new ArrayList<>();
            for(Role role:user.getRoles()){
                roles.add(roleAdapter.modelToEntity(role));
            }
            userEntity.setRoles(roles);
        }
        return userEntity;
    }
}
