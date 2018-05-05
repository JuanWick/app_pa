package fr.esgi.reporitories.carts.adapter;

import entities.Cart;
import entities.Product;
import entities.User;
import fr.esgi.reporitories.carts.dao.RCartProductEntity;
import fr.esgi.reporitories.carts.dao.RSharedCartEntity;
import fr.esgi.reporitories.carts.dao.TCartEntity;
import fr.esgi.reporitories.products.dao.TProductEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SharedCartAdapter {

    List<User> adapt(Collection<RSharedCartEntity> cartShared){
        List<User> sharedUsers = new ArrayList<>();

        for(RSharedCartEntity entity:cartShared){

            TUserEntity userEntity = entity.gettUserByUserId();
            User currentUser = new User();
            currentUser.setId(userEntity.getId());
            currentUser.setName(userEntity.getName());
            currentUser.setFirstName(userEntity.getFirstname());
            sharedUsers.add(currentUser);
        }
        return sharedUsers;
    }

    Collection<RSharedCartEntity> convert(Cart cart){
        ArrayList<RSharedCartEntity> shareds = new ArrayList<>();

        for(User user:cart.getSharedUsers()){
            RSharedCartEntity rSharedCartEntity = new RSharedCartEntity();

            TCartEntity cartEntity = new TCartEntity();
            cartEntity.setId(cart.getId());
            rSharedCartEntity.settCartByCartId(cartEntity);

            TUserEntity userEntity = new TUserEntity();
            userEntity.setId(user.getId());
            rSharedCartEntity.settUserByUserId(userEntity);

            shareds.add(rSharedCartEntity);
        }
        return shareds;
    }
}
