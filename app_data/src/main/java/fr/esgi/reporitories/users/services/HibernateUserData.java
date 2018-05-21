package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.User;
import fr.esgi.reporitories.users.RoleRepository;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.adapter.RoleDataAdapter;
import fr.esgi.reporitories.users.adapter.UserDataAdapter;
import fr.esgi.reporitories.users.dao.TRoleEntity;
import fr.esgi.reporitories.users.dao.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserData implements UserData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDataAdapter userDataAdapter;

    @Autowired
    RoleDataAdapter roleDataAdapter;

    @Override
    public Integer create(User user) {
        TUserEntity userEntity = userRepository.save(userDataAdapter.modelToEntity(user, true));
        return userEntity.getId();
    }

    @Override
    public User getById(int id) {
        User user = null;
        if(userRepository.findById(id).isPresent()){
            TUserEntity  tUserEntity = userRepository.findById(id).get();
            user = userDataAdapter.entityToModel(tUserEntity, true);
        }
        return user;
    }

    @Override
    public User save(User user) {
        TUserEntity userEntity = userRepository.save(userDataAdapter.modelToEntity(user, true));
        return userDataAdapter.entityToModel(userEntity, true);
    }

    @Override
    public List<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        List<TRoleEntity> roleEntities = (List<TRoleEntity>) roleRepository.findAll();

        for(TRoleEntity roleEntity : roleEntities){
            Role role = roleDataAdapter.entityToModel(roleEntity);
            roles.add(role);
        }

        return roles;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {
        if(userRepository.findById(id).isPresent()){
            TUserEntity userEntity = userRepository.findById(id).get();

            if(null != userEntity){
            userRepository.delete(userEntity);
            }
        }
    }
}
