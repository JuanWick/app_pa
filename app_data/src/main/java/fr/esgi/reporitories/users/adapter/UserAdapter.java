package fr.esgi.reporitories.users.adapter;

import entities.User;
import fr.esgi.reporitories.users.dao.TUserEntity;

public class UserAdapter {
    public User adapt(TUserEntity userEntity){
        User user = new User();
        user.setFirstName(userEntity.getFirstname());
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        return user;
    }

    public TUserEntity convert(User user){
        TUserEntity userEntity = new TUserEntity();
        if(null != user.getId()){
            userEntity.setId(user.getId());
        }
        userEntity.setFirstname(user.getFirstName());
        userEntity.setName(user.getName());
        return userEntity;
    }
}
