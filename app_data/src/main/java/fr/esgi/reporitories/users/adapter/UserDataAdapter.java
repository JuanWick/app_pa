package fr.esgi.reporitories.users.adapter;

import entities.Cart;
import entities.Role;
import entities.User;
import fr.esgi.reporitories.carts.adapter.CartDataAdapter;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataAdapter {

    @Autowired
    CartDataAdapter cartDataAdapter;

    @Autowired
    RoleDataAdapter roleDataAdapter;

    public UserDataAdapter() {
    }

    public User entityToModel(TUserEntity userEntity, boolean all){
        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setRgpdAccepted(userEntity.getRgpdAccepted());
        user.setRgpdAcceptedDate(userEntity.getRgpdAcceptedDate());

        if(all && null != userEntity.getCarts()){
            List<Cart> carts = new ArrayList<>();
            for(TCartEntity cart:userEntity.getCarts()){
                carts.add(cartDataAdapter.entityToModel(cart, false));
            }
            user.setCarts(carts);
        }

        if(all && null != userEntity.getRoles()){
            List<Role> roles = new ArrayList<>();
            for(TRoleEntity role:userEntity.getRoles()){
                roles.add(roleDataAdapter.entityToModel(role));
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
        userEntity.setRgpdAccepted(user.isRgpdAccepted());
        userEntity.setRgpdAcceptedDate(user.getRgpdAcceptedDate());

        if(all && null != user.getCarts()){
            List<TCartEntity> carts = new ArrayList<>();
            for(Cart cart:user.getCarts()){
                carts.add(cartDataAdapter.modelToEntity(cart, false));
            }
            userEntity.setCarts(carts);
        }

        if(all && null != user.getRoles()){
            List<TRoleEntity> roles = new ArrayList<>();
            for(Role role:user.getRoles()){
                roles.add(roleDataAdapter.modelToEntity(role));
            }
            userEntity.setRoles(roles);
        }
        return userEntity;
    }
}
