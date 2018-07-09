package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.UserAuthenticator;
import fr.esgi.reporitories.users.UserAuthenticatorRepository;
import fr.esgi.reporitories.users.adapter.RoleDataAdapter;
import fr.esgi.reporitories.users.adapter.UserAuthenticatorDataAdapter;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserAuthenticatorData implements UserAuthenticatorData{

    @Autowired
    UserAuthenticatorRepository userAuthenticatorRepository;

    @Autowired
    UserAuthenticatorDataAdapter userAuthenticatorDataAdapter;

    @Autowired
    RoleDataAdapter roleDataAdapterAdapter;

    @Override
    public UserAuthenticator save(UserAuthenticator userAuthenticator) {
        return userAuthenticatorDataAdapter.entityToModel(userAuthenticatorRepository.save(userAuthenticatorDataAdapter.modelToEntity(userAuthenticator)));
    }

    @Override
    public UserAuthenticator findByUsername(String username) {
        if(null != userAuthenticatorRepository.findByLogin(username)){
            return userAuthenticatorDataAdapter.entityToModel(userAuthenticatorRepository.findByLogin(username));
        } else {
            return null;
        }
    }

    @Override
    public UserAuthenticator findById(Integer userid) {
        if(null != userAuthenticatorRepository.findById(userid)){
            return userAuthenticatorDataAdapter.entityToModel(userAuthenticatorRepository.findById(userid).get());
        } else {
            return null;
        }
    }

    @Override
    public UserAuthenticator findByEmail(String email) {
        if(null != userAuthenticatorRepository.findByEmail(email)){
            return userAuthenticatorDataAdapter.entityToModel(userAuthenticatorRepository.findByEmail(email));
        } else {
            return null;
        }
    }

    @Override
    public List<String> getRolesByUserAuthenticator(Integer userId) {
        List<IRole> results = userAuthenticatorRepository.getRolesByUserAuthenticator(userId);
        List<String> roles = new ArrayList<>();

        for(IRole ro : results){
            roles.add(ro.getName());
        }
        return roles;
    }
}
