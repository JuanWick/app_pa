package fr.esgi.reporitories.users.adapter;

import entities.UserAuthenticator;
import fr.esgi.reporitories.users.dao.TUserAuthenticator;

public class UserAuthenticatorDataAdapter {

    private UserDataAdapter userDataAdapter = new UserDataAdapter();

    public UserAuthenticator entityToModel(TUserAuthenticator userAuthenticatorEntity){
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        userAuthenticator.setId(userAuthenticatorEntity.getId());
        userAuthenticator.setLogin(userAuthenticatorEntity.getLogin());
        userAuthenticator.setPassword(userAuthenticatorEntity.getPassword());
        userAuthenticator.setEmail(userAuthenticatorEntity.getEmail());
        userAuthenticator.setUser(userDataAdapter.entityToModel(userAuthenticatorEntity.getUser(),false));
        return userAuthenticator;
    }

    public TUserAuthenticator modelToEntity(UserAuthenticator userAuthenticator){
        TUserAuthenticator userAuthenticatorEntity = new TUserAuthenticator();
        if(null != userAuthenticator.getId()){
            userAuthenticatorEntity.setId(userAuthenticator.getId());
        }
        userAuthenticatorEntity.setLogin(userAuthenticator.getLogin());
        userAuthenticatorEntity.setPassword(userAuthenticator.getPassword());
        userAuthenticatorEntity.setEmail(userAuthenticator.getEmail());
        userAuthenticatorEntity.setUser(userDataAdapter.modelToEntity(userAuthenticator.getUser(),false));
        return userAuthenticatorEntity;
    }
}
