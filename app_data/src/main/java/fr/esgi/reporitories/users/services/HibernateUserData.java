package fr.esgi.reporitories.users.services;

import entities.Role;
import entities.User;
import fr.esgi.reporitories.users.RoleRepository;
import fr.esgi.reporitories.users.UserRepository;
import fr.esgi.reporitories.users.adapter.RoleAdapter;
import fr.esgi.reporitories.users.adapter.UserAdapter;
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
    UserAdapter userAdapter;

    @Autowired
    RoleAdapter roleAdapter;

    @Override
    public Integer create(User user) {
        TUserEntity userEntity = userRepository.save(userAdapter.modelToEntity(user, true));
        return userEntity.getId();
    }

    @Override
    public User getById(int id) {
        User user = null;
        if(userRepository.findById(id).isPresent()){
            TUserEntity  tUserEntity = userRepository.findById(id).get();
            user = userAdapter.entityToModel(tUserEntity, true);
        }
        return user;
    }

    @Override
    public User save(User user) {
        TUserEntity userEntity = userRepository.save(userAdapter.modelToEntity(user, true));
        return userAdapter.entityToModel(userEntity, true);
    }

    @Override
    public List<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        List<TRoleEntity> roleEntities = (List<TRoleEntity>) roleRepository.findAll();

        for(TRoleEntity roleEntity : roleEntities){
            Role role = roleAdapter.entityToModel(roleEntity);
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

    }
}
