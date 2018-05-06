package fr.esgi.reporitories.users.adapter;

import entities.User;
import fr.esgi.reporitories.users.dao.TUserEntity;

public class UserAdapter {
    public User entityToModel(TUserEntity userEntity){
        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        return user;
    }

    public TUserEntity modelToEntity(User user){
        TUserEntity userEntity = new TUserEntity();
        if(null != user.getId()){
            userEntity.setId(user.getId());
        }
        userEntity.setFirstName(user.getFirstName());
        userEntity.setName(user.getName());
        return userEntity;
    }
}
